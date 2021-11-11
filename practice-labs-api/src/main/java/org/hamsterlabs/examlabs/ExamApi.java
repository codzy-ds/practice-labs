package org.hamsterlabs.examlabs;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hamsterlabs.examlabs.entities.Exam;
import org.hamsterlabs.examlabs.repos.ExamsRepository;

@Path("/exams")
public class ExamApi {
   
   @Inject
   private ExamsRepository repo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Exam> getAllExams() {
        return repo.list();
    }
}