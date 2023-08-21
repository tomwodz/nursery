package pl.tomwodz.nursery.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tomwodz.nursery.model.GroupChildren;
import pl.tomwodz.nursery.services.IGroupChildrenService;

import java.util.Optional;

@Controller
@RequestMapping(path="/view/groupchildren")
@AllArgsConstructor
public class GroupChildrenViewController {

    private final IGroupChildrenService groupChildrenService;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("groupChildren", this.groupChildrenService.findAll());
        return "groupchildren";
    }

    @GetMapping(path = "/{id}")
    public String getChildById(Model model, @PathVariable Long id){
        Optional<GroupChildren> groupChildrenBox = this.groupChildrenService.findById(id);
        if(groupChildrenBox.isPresent()){
            model.addAttribute("group", groupChildrenBox.get());
            return "sample-groupchildren";
        }
        model.addAttribute("message", "Nie znaleziono grupy o id: " + id);
        return "message";
    }

}
