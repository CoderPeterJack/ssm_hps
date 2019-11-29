package com.jp.controller;

import com.jp.dto.MoodDTO;
import com.jp.service.MoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
