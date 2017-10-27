package com.microsoft.demo.controller;

import java.util.List;

import com.microsoft.demo.model.Project;
import com.microsoft.demo.repository.ProjectRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value="project", description="Operations on Project entities.")
public class ProjectsController {

    @Autowired
    private ProjectRepository projectRepository;

    @PreAuthorize("hasRole('ROLE_javadevs')")
    @RequestMapping(value = "api/projects", method = RequestMethod.GET)
    public List<Project> list() {
        return projectRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_javadevs')")
    @RequestMapping(value = "api/projects", method = RequestMethod.POST)
    public Project create(@RequestBody Project project) {
        return projectRepository.saveAndFlush(project);
    }

    @PreAuthorize("hasRole('ROLE_javadevs')")
    @RequestMapping(value = "api/projects/{id}", method = RequestMethod.GET)
    public Project get(@PathVariable Long id) {
        return projectRepository.findOne(id);
    }

    @PreAuthorize("hasRole('ROLE_javadevs')")
    @RequestMapping(value = "api/projects/{id}", method = RequestMethod.PUT)
    public Project update(@PathVariable Long id, @RequestBody Project project) {
        Project existingProject = projectRepository.findOne(id);
        BeanUtils.copyProperties(project, existingProject);
        return projectRepository.saveAndFlush(existingProject);
    }
    @PreAuthorize("hasRole('ROLE_javadevs')")
    @RequestMapping(value = "api/projects/{id}", method = RequestMethod.DELETE)
    public Project delete(@PathVariable Long id) {
        Project existingProject = projectRepository.findOne(id);
        projectRepository.delete(existingProject);
        return existingProject;
    }
}


