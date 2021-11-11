package org.hamsterlabs.examlabs.entities;

import java.util.List;

public class Questionnaire {

   private Exam exam;
   private List<Question> questions;

   public Questionnaire(Exam exam, List<Question> questions) {
      this.exam = exam;
      this.questions = questions;
      generateQuestionnaire();
   }

   private void generateQuestionnaire() {
      this.questions = exam.generateExam(questions);
   }

   public Exam getExam() {
      return exam;
   }

   public void setExam(Exam exam) {
      this.exam = exam;
   }

   public List<Question> getQuestions() {
      return questions;
   }

   public void setQuestions(List<Question> questions) {
      this.questions = questions;
   }

}
