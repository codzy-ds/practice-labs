package org.hamsterlabs.examlabs.entities;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.Collectors;

public class Exam {
   private String name;
   private Map<String, Integer> categories;
   private Integer numQuestions;
   private Integer examTime;
   private Integer success;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Map<String, Integer> getCategories() {
      return categories;
   }

   public void setCategories(List<Category> categories) {
      this.categories = categories.stream().collect(Collectors.toMap(Category::getName, Category::getValue));
   }

   public Integer getNumQuestions() {
      return numQuestions;
   }

   public void setNumQuestions(Integer numQuestions) {
      this.numQuestions = numQuestions;
   }

   public Integer getExamTime() {
      return examTime;
   }

   public void setExamTime(Integer examTime) {
      this.examTime = examTime;
   }

   public Integer getSuccess() {
      return success;
   }

   public void setSuccess(Integer success) {
      this.success = success;
   }

   public List<Question> generateExam(List<Question> questions) {
      List<Question> newList = questions.stream().collect(Collectors.groupingBy(Question::getSubject))
            .entrySet().stream().map(this::filterCategory).flatMap(List::stream).collect(Collectors.toList());
      Collections.shuffle(newList);
      return newList;
   }
   
   private List<Question> filterCategory(Entry<String, List<Question>> entry) {
      Double value = Math.ceil(categories.getOrDefault(entry.getKey(), 0) * numQuestions / 100.0);
      
      Collections.shuffle(entry.getValue());
      List<Question> filtered = entry.getValue().subList(0, Math.min(value.intValue(), entry.getValue().size()));
      if(value > filtered.size()) {
         Random rand = new Random();
         for(int i = 0; i <= value-filtered.size(); i++) {
            Question clonee = filtered.get(rand.nextInt(filtered.size()));
            filtered.add(new Question(clonee.getQuestion(), clonee.getAnswer(), clonee.getSubject(), clonee.getExam()));
         }
      }
      return filtered;
   }
}
