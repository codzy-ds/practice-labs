package org.hamsterlabs.examlabs;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hamsterlabs.examlabs.entities.Exam;
import org.hamsterlabs.examlabs.entities.Questionnaire;
import org.hamsterlabs.examlabs.repos.ExamsRepository;
import org.hamsterlabs.examlabs.repos.QuestionnaireRepository;

@Path("/questions")
public class QuestionnaireApi {

   @Inject
   private QuestionnaireRepository questionnaireRepo;

   @Inject
   private ExamsRepository examRepo;

   @GET
   @Path("{exam}")
   @Produces(MediaType.APPLICATION_JSON)
   public Questionnaire getQuestionnaireForExam(@PathParam("exam") String exam) {
      System.out.println(exam);
      Exam examObj = examRepo.getExam(exam);
      return questionnaireRepo.listForExam(examObj);
   }

}
