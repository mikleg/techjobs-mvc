package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {


    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "")
    public String search(Model model)
    {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value = "results")
    public String listColumnValues(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
            ArrayList<HashMap<String, String>> items = new ArrayList<>();

        if (!searchType.equals("all"))
            {
                items = JobData.findByColumnAndValue(searchType, searchTerm);
            }
            else
            {
                items = JobData.findByValue(searchTerm);
            }


            model.addAttribute("title", "All " + ListController.columnChoices.get(searchType) + " Values");
            model.addAttribute("column", searchType);
            model.addAttribute("items", items);
            model.addAttribute("par1", searchType);
            model.addAttribute("par2", searchTerm);

            return "search";


    }



}
