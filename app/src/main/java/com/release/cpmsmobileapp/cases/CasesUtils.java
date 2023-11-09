package com.release.cpmsmobileapp.cases;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.release.cpmsmobileapp.responsebody.SearchCaseResponse;
import com.release.cpmsmobileapp.utils.HelperUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CasesUtils extends HelperUtils {
    Context context;

    public CasesUtils(Context context) {
        super(context);
        this.context = context;
    }

    public List<SearchCaseResponse> populateList(List<SearchCaseResponse> list) {
//        if(list.size()==0) return list;
//        List<SearchCaseResponse> List=new ArrayList<>();
        SearchCaseResponse caseResponse = list.get(0);
        Integer cnt = 0;

        String[] statuses = {"In Process", "Complete", "Appearance", "Evidence"};
        for (String status : statuses) {
            cnt++;
            SearchCaseResponse response = new SearchCaseResponse(String.valueOf(cnt), caseResponse.getYear(),
                    caseResponse.getReg_date(), caseResponse.getCase_detail_id(), caseResponse.getAct_section(), caseResponse.getCase_detail()
                    , caseResponse.getRemarks(), caseResponse.getNodal_officer(), status, caseResponse.getPs_name(), caseResponse.getPs_name_hi(), caseResponse.getDistrict_name(), caseResponse.getDistrict_name_hi()
                    , caseResponse.getMajorHead_name(), caseResponse.getMajorHead_name_hi(), caseResponse.getMinorHead_name(), caseResponse.getMinorHead_name_hi());
//            SearchCaseResponse response = caseResponse;
//            response.setCaseStatus_name(status);
            list.add(response);
        }
        return list;
    }

    public List<SearchCaseResponse> updateList(List<SearchCaseResponse> list,CasesFilter filterObj) {
        List<SearchCaseResponse> tempList = list;

        if (filterObj.getFIR_no().equals("") && filterObj.getCase_detail_id().equals("")
                && filterObj.getPs_name().equals("") && filterObj.getAct_section().equals("")
                && filterObj.getStart_date().equals("")) {
            return tempList;
        }
        if(!filterObj.getStart_date().equals("")) {
            try {
                tempList = filterResponsesByDate(tempList, filterObj.getStart_date(), filterObj.getEnd_date());
            } catch (ParseException e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        if(!filterObj.getAct_section().equals("")){
            List<SearchCaseResponse> filteredList = new ArrayList<>();
            for(SearchCaseResponse response: tempList)
                if(response.getAct_section().toLowerCase().contains(filterObj.getAct_section().toLowerCase()))
                    filteredList.add(response);
            tempList = filteredList;
        }

        if(!filterObj.getCase_detail_id().equals("")){
            List<SearchCaseResponse> filteredList = new ArrayList<>();
            for(SearchCaseResponse response: tempList)
                if(String.valueOf(response.getCase_detail_id()).equalsIgnoreCase(filterObj.getCase_detail_id()))
                    filteredList.add(response);
            tempList = filteredList;
        }

        if(!filterObj.getFIR_no().equals("")){
            List<SearchCaseResponse> filteredList = new ArrayList<>();
            for(SearchCaseResponse response: tempList)
                if(String.valueOf(response.getFIR_no()).equalsIgnoreCase(filterObj.getFIR_no()))
                    filteredList.add(response);
            tempList = filteredList;
        }

        return tempList;
    }

    public static List<SearchCaseResponse> filterResponsesByDate(List<SearchCaseResponse> list, String startDateStr, String endDateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = dateFormat2.parse(startDateStr);
        Date endDate = dateFormat2.parse(endDateStr);

        List<SearchCaseResponse> filteredResponses = new ArrayList<>();

        for (SearchCaseResponse response : list) {
            Date dob = dateFormat.parse(response.getReg_date());
            if (dob.compareTo(startDate) >= 0 && dob.compareTo(endDate) <= 0) {
                filteredResponses.add(response);
            }
        }

        return filteredResponses;
    }
}
