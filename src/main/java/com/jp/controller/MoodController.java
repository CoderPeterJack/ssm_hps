package com.jp.controller;

import com.jp.dto.MoodDTO;
import com.jp.model.Mood;
import com.jp.service.MoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

import static java.lang.System.*;

/**
 * @program: HighConcurrentPraise
 * @description: 说说控制层
 * @author: CoderPengJiang
 * @create: 2019-10-31 22:57
 **/
@Controller
@RequestMapping("/mood")
public class MoodController {
    @Resource
    private MoodService moodService;

    @RequestMapping("/findAll")
    public String findAll(Model model) {
        List<MoodDTO> moodDTOList = moodService.findAll();
        model.addAttribute("moods", moodDTOList);
        return "mood";
    }

    @GetMapping(value ="/{moodId}/praise")
    public String prasie(Model model, @PathVariable(value="moodId")
                         Integer moodId, @RequestParam(value = "useId",required=false)Integer userId){
        boolean isPraise=moodService.praiseMood(userId,moodId);

        List<MoodDTO> moodDTOList=moodService.findAll();
        model.addAttribute("moods",moodDTOList);
        model.addAttribute("isPraise",isPraise);
        return "mood";

    }
}
