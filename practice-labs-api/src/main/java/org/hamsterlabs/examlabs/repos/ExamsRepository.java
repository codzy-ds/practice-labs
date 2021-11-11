package org.hamsterlabs.examlabs.repos;

import static com.mongodb.client.model.Filters.eq;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.bson.Document;
import org.hamsterlabs.examlabs.entities.Category;
import org.hamsterlabs.examlabs.entities.Exam;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

@ApplicationScoped
public class ExamsRepository {

   @Inject
   MongoClient mongoClient;

   public List<Exam> list() {
      List<Exam> list = new ArrayList<>();
      MongoCursor<Document> cursor = getCollection().find().iterator();

      try {
         while (cursor.hasNext()) {
            list.add(buildExam(cursor.next()));
         }
      } finally {
         cursor.close();
      }
      return list;
   }

   private Exam buildExam(Document document) {
      Exam exam = new Exam();
      @SuppressWarnings("unchecked")
      List<Document> categories = (List<Document>) document.get("categories");
      exam.setCategories(categories.stream().map(d -> new Category(d.getString("name"), d.getInteger("value")))
            .collect(toList()));
      exam.setName(document.getString("name"));
      exam.setExamTime(document.getInteger("examTime"));
      exam.setSuccess(document.getInteger("success"));
      exam.setNumQuestions(document.getInteger("numQuestions"));
      return exam;
   }

   private MongoCollection<Document> getCollection() {
      return mongoClient.getDatabase("kubes-certif").getCollection("exams");
   }

   public Exam getExam(String name) {
      MongoCursor<Document> cursor = getCollection().find(eq("name", name)).iterator();
      Exam exam = null;

      try {
         if(cursor.hasNext()) {
            exam = buildExam(cursor.next());
         }
      } finally {
         cursor.close();
      }
      return exam;
   }
}
