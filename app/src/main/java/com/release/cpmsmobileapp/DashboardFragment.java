package com.release.cpmsmobileapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.release.cpmsmobileapp.adapters.HearingListAdapter;
import com.release.cpmsmobileapp.adapters.NoticeListAdapter;
import com.release.cpmsmobileapp.requestbody.ByDateRequestBody;
import com.release.cpmsmobileapp.responsebody.HearingListResponse;
import com.release.cpmsmobileapp.responsebody.NoticesResponse;
import com.release.cpmsmobileapp.utils.HelperUtils;
import com.release.cpmsmobileapp.utils.SwipeDetector;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    View view;
    String token;
    TextView tv;
    private ApiInterface apiInterface;
    private String gpfNo;
    TextView hearingHeader;
    private RecyclerView hearingsRV;
    private HearingListAdapter hearingListAdapter;
    private RecyclerView noticesRV;
    private NoticeListAdapter noticeListAdapter;
    TextView noticeHeader;
    ImageView increaseDateBtn, decreaseDateBtn;
    LinearLayout noNoticeError, noHearingError;
    CardView datePickerCard;
    private FrameLayout progressBar1,progressBar2;
    private HorizontalScrollView hsv1,hsv2;
    @SuppressLint("SimpleDateFormat")
    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initializations();
        loadToken();
        setRecyclerViews();
        setDate();
        pickDate();
        increaseDateBtn();
        decreaseDateBtn();
        dateSwipeDetector();
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void dateSwipeDetector() {
        SwipeDetector swipeDetector = new SwipeDetector(new SwipeDetector.OnSwipeListener() {
            @Override
            public void onSwipeRight() {
                String newDate = decreaseDate(tv.getText().toString());
                String[] dateParts = newDate.split("/");
                try {
                    changeDate(dateParts[2], dateParts[1], dateParts[0]);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public void onSwipeLeft() {
                String newDate = increaseDate(tv.getText().toString());
                String[] dateParts = newDate.split("/");
                try {
                    changeDate(dateParts[2], dateParts[1], dateParts[0]);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        datePickerCard.setOnTouchListener(swipeDetector);
    }

    private void decreaseDateBtn() {
        decreaseDateBtn.setOnClickListener(view1 -> {
            String newDate = decreaseDate(tv.getText().toString());
            String[] dateParts = newDate.split("/");
            try {
                changeDate(dateParts[2], dateParts[1], dateParts[0]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void increaseDateBtn() {
        increaseDateBtn.setOnClickListener(view1 -> {
            String newDate = increaseDate(tv.getText().toString());
            String[] dateParts = newDate.split("/");
            try {
                changeDate(dateParts[2], dateParts[1], dateParts[0]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void setRecyclerViews() {
        hearingsRV.setLayoutManager(new LinearLayoutManager(getContext()));
        hearingListAdapter = new HearingListAdapter();
        hearingsRV.setAdapter(hearingListAdapter);
        noticesRV.setLayoutManager(new LinearLayoutManager(getContext()));
        noticeListAdapter = new NoticeListAdapter();
        noticesRV.setAdapter(noticeListAdapter);
    }

    private void getHearingByDate(String date) {
        gpfNo = "123";
        ByDateRequestBody requestBody = new ByDateRequestBody(gpfNo, date);
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", "Bearer " + token);
        Call<List<HearingListResponse>> call = apiInterface.getHearingsByDate(map, requestBody);
        call.enqueue(new Callback<List<HearingListResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<HearingListResponse>> call, @NonNull Response<List<HearingListResponse>> response) {
                List<HearingListResponse> list = response.body();
                if (list == null || response.body() == null) {
                    progressBar1.setVisibility(View.GONE);
                    hsv1.setVisibility(View.GONE);
                    toastShort("Could not get cases\n\terror code : " + response.code(), getContext());
                    return;
                }
                hearingListAdapter.setHearingListAdapter(list, getContext());
                progressBar1.setVisibility(View.GONE);
                if (list.size() > 0) {
                    noHearingError.setVisibility(View.GONE);
                    hsv1.setVisibility(View.VISIBLE);
                } else {
                    noHearingError.setVisibility(View.VISIBLE);
                    hsv1.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<HearingListResponse>> call, Throwable t) {
                toastShort("Error : " + t.getMessage(), getContext());
                progressBar1.setVisibility(View.GONE);
                noHearingError.setVisibility(View.VISIBLE);
            }
        });
    }

    private void getNoticesByDate(String date) {
        gpfNo = "123";
        ByDateRequestBody requestBody = new ByDateRequestBody(gpfNo, date);
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", "Bearer " + token);
        Call<List<NoticesResponse>> call = apiInterface.getNoticesByDate(map, requestBody);
        call.enqueue(new Callback<List<NoticesResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<NoticesResponse>> call, @NonNull Response<List<NoticesResponse>> response) {
                List<NoticesResponse> list = response.body();
                if (list == null || response.body() == null) {
                    progressBar2.setVisibility(View.GONE);
                    hsv2.setVisibility(View.GONE);
                    toastShort("Could not get cases\n\terror code : " + response.code(), getContext());
                    return;
                }
                noticeListAdapter.setNoticeListAdapter(list, getContext());
                progressBar2.setVisibility(View.GONE);
                if (list.size() > 0) {
                    noNoticeError.setVisibility(View.GONE);
                    hsv2.setVisibility(View.VISIBLE);
                } else {
                    noNoticeError.setVisibility(View.VISIBLE);
                    hsv2.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<NoticesResponse>> call, Throwable t) {
                progressBar2.setVisibility(View.GONE);
                toastShort("Error : " + t.getMessage(), getContext());
                noNoticeError.setVisibility(View.VISIBLE);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setDate() {
        String yyyy, mm, dd;
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        yyyy = String.valueOf(year);
        mm = month < 10 ? "0" + month : String.valueOf(month);
        dd = day < 10 ? "0" + day : String.valueOf(day);
        changeDate(yyyy, mm, dd);
    }

    public static String increaseDate(String currentDate) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(Objects.requireNonNull(dateFormat.parse(currentDate)));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            return dateFormat.format(calendar.getTime());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decreaseDate(String currentDate) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(Objects.requireNonNull(dateFormat.parse(currentDate)));
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            return dateFormat.format(calendar.getTime());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @SuppressLint("SetTextI18n")
    private void pickDate() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        tv.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        String y = String.valueOf(year1);
                        String m = (monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : String.valueOf(monthOfYear + 1);
                        String d = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                        changeDate(y, m, d);
                    }, year, month, day);
            datePickerDialog.show();
        });
    }

    @SuppressLint("SetTextI18n")
    private void changeDate(String yyyy, String mm, String dd) {
        tv.setText(dd + "/" + mm + "/" + yyyy);
        String date = yyyy + "-" + mm + "-" + dd;
        progressBar1.setVisibility(View.VISIBLE);
        progressBar2.setVisibility(View.VISIBLE);
        getHearingByDate(date);
        getNoticesByDate(date);
    }

    private void initializations() {
        tv = view.findViewById(R.id.dateBox);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        HelperUtils utils = new HelperUtils(requireContext());
        token = utils.getToken();
        gpfNo = utils.getGpfNo();
        hearingHeader = view.findViewById(R.id.hearingHeader);
        hearingsRV = view.findViewById(R.id.hearingsByDateRecyclerView);
        noticesRV = view.findViewById(R.id.noticesByDateRecyclerView);
        noticeHeader = view.findViewById(R.id.noticeHeader);
        increaseDateBtn = view.findViewById(R.id.increaseDateBtn);
        decreaseDateBtn = view.findViewById(R.id.decreaseDateBtn);
        noNoticeError = view.findViewById(R.id.noNoticeContainer);
        noHearingError = view.findViewById(R.id.noHearingContainer);
        datePickerCard = view.findViewById(R.id.datepickerCard);
        progressBar1 = view.findViewById(R.id.progressBar1);
        progressBar2 = view.findViewById(R.id.progressBar2);
        hsv1=view.findViewById(R.id.hsv1);
        hsv2=view.findViewById(R.id.hsv2);
    }


    private void loadToken() {
        if (token.length() == 0) {
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            startActivity(intent);
            requireActivity().finish();
        }
    }

    private void toastShort(String string, Context context) {
        if (context != null) {
            Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
        }
    }
}