package com.microsoft.demo.controller;

import java.util.List;

import com.microsoft.demo.model.Project;
import com.microsoft.demo.repository.ProjectRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class ProjectsController {

    @Autowired
    private ProjectRepository projectRepository;

    @RequestMapping(value = "projects", method = RequestMethod.GET)
    public List<Project> list() {
        return projectRepository.findAll();
    }

    @RequestMapping(value = "projects", method = RequestMethod.POST)
    public Project create(@RequestBody Project project) {
        return projectRepository.saveAndFlush(project);
    }

    @RequestMapping(value = "projects/{id}", method = RequestMethod.GET)
    public Project get(@PathVariable Long id) {
        return projectRepository.findOne(id);
    }

    @RequestMapping(value = "projects/{id}", method = RequestMethod.PUT)
    public Project update(@PathVariable Long id, @RequestBody Project project) {
        Project existingProject = projectRepository.findOne(id);
        BeanUtils.copyProperties(project, existingProject);
        return projectRepository.saveAndFlush(existingProject);
    }

    @RequestMapping(value = "projects/{id}", method = RequestMethod.DELETE)
    public Project delete(@PathVariable Long id) {
        Project existingProject = projectRepository.findOne(id);
        projectRepository.delete(existingProject);
        return existingProject;
    }
}


