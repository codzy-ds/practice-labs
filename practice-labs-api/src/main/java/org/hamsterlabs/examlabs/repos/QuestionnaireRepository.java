package org.hamsterlabs.examlabs.repos;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.bson.Document;
import org.hamsterlabs.examlabs.entities.Exam;
import org.hamsterlabs.examlabs.entities.Question;
import org.hamsterlabs.examlabs.entities.Questionnaire;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

@ApplicationScoped
public class QuestionnaireRepository {
   
   @Inject
   MongoClient mongoClient;

   public Questionnaire listForExam(Exam exam) {
      List<Question> list = new ArrayList<>();
      MongoCursor<Document> cursor = getCollection().find(Filters.eq("exam", exam.getName())).iterator();
      
      try {
         while (cursor.hasNext()) {
            Document document = cursor.next();
            Question question = new Question();
            question.setQuestion(document.getString("question"));
            question.setAnswer(document.getString("answer"));
            question.setSubject(document.getString("subject"));
            question.setExam(document.getString("exam"));
            list.add(question);
         }
      } finally {
         cursor.close();
      }
      return new Questionnaire(exam, list);
   }
   
   private MongoCollection<Document> getCollection() {
      return mongoClient.getDatabase("kubes-certif").getCollection("questions");
   }
}
