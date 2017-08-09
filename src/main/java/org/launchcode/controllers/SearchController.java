package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {
    private String lastChoise = "core competency";

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "")
    public String search(Model model)
    {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value = "results")
    public String listColumnValues(Model model, @RequestParam Optional<String> searchType, @RequestParam String searchTerm) {
            ArrayList<HashMap<String, String>> items = new ArrayList<>();
            String aSearchType = "";

            StringBuilder rrrr = new StringBuilder("");
            searchType.ifPresent(value -> rrrr.append(value));


            if (searchType.isPresent()){
                aSearchType += rrrr.toString();
            }
            else {
                aSearchType = lastChoise;
            }
                if (!aSearchType.equals("all")) {
                    items = JobData.findByColumnAndValue(aSearchType, searchTerm);
                } else {
                    items = JobData.findByValue(searchTerm);
                }
                lastChoise = aSearchType;

                model.addAttribute("title", "All " + ListController.columnChoices.get(aSearchType) + " Values");
                model.addAttribute("column", aSearchType);
                model.addAttribute("items", items);
                /*model.addAttribute("par1", aSearchType);
                model.addAttribute("par2", searchTerm);*/

            return "search";


    }



}
