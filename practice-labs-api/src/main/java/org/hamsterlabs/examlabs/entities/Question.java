package org.hamsterlabs.examlabs.entities;

public class Question {

   private String question;
   private String answer;
   private String subject;
   private String exam;

   public Question() {
   }

   public Question(String question, String answer, String subject, String exam) {
      this.answer = answer;
      this.question = question;
      this.subject = subject;
      this.exam = exam;
   }

   public String getQuestion() {
      return question;
   }

   public void setQuestion(String question) {
      this.question = question;
   }

   public String getAnswer() {
      return answer;
   }

   public void setAnswer(String answer) {
      this.answer = answer;
   }

   public String getSubject() {
      return subject;
   }

   public void setSubject(String subject) {
      this.subject = subject;
   }

   public String getExam() {
      return exam;
   }

   public void setExam(String exam) {
      this.exam = exam;
   }

}
