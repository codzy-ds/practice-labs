package org.hamsterlabs.examlabs.entities;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

public class ExamTest {

   private Faker faker = new Faker();

   private final static String CATEGORY_1 = "CATEGORY_1";
   private final static String CATEGORY_2 = "CATEGORY_2";
   private final static String CATEGORY_3 = "CATEGORY_3";
   private final static String CATEGORY_4 = "CATEGORY_4";

   @Test
   public void givenAnExamCDAKAndAListOfQuestions_whenGenerateExam_ThenListOfQuestionsFiltered() {
      Exam exam = new Exam();
      exam.setCategories(getListCategories());
      exam.setNumQuestions(20);
      List<Question> result = exam.generateExam(getListQuestions(12));
      Assertions.assertThat(result).hasSize(20);
      Assertions.assertThat(result.stream().filter(q -> q.getSubject().equals(CATEGORY_1)).collect(toList()))
            .hasSize(2);
      Assertions.assertThat(result.stream().filter(q -> q.getSubject().equals(CATEGORY_2)).collect(toList()))
            .hasSize(8);
      Assertions.assertThat(result.stream().filter(q -> q.getSubject().equals(CATEGORY_3)).collect(toList()))
            .hasSize(10);
   }

   @Test
   public void givenAnExamCDAKAndAListOfQuestionsWithPercNotJust_whenGenerateExam_ThenListOfQuestionsFilteredAndListHasMoreQuestions() {
      Exam exam = new Exam();
      exam.setCategories(getListCategories());
      exam.setNumQuestions(19);
      List<Question> result = exam.generateExam(getListQuestions(12));
      Assertions.assertThat(result).hasSize(20);
      Assertions.assertThat(result.stream().filter(q -> q.getSubject().equals(CATEGORY_1)).collect(toList()))
            .hasSize(2);
      Assertions.assertThat(result.stream().filter(q -> q.getSubject().equals(CATEGORY_2)).collect(toList()))
            .hasSize(8);
      Assertions.assertThat(result.stream().filter(q -> q.getSubject().equals(CATEGORY_3)).collect(toList()))
            .hasSize(10);
   }

   @Test
   public void givenAnExamCDAKAndAListOfQuestionsWithNotEnoughCategory1_whenGenerateExam_ThenListOfQuestionsFilteredAndListHasEnoughQuestionRepeated() {
      Exam exam = new Exam();
      exam.setCategories(getListCategories());
      exam.setNumQuestions(20);
      List<Question> result = exam.generateExam(getListQuestions(8));
      Assertions.assertThat(result).hasSize(20);
      Assertions.assertThat(result.stream().filter(q -> q.getSubject().equals(CATEGORY_1)).collect(toList()))
            .hasSize(2);
      Assertions.assertThat(result.stream().filter(q -> q.getSubject().equals(CATEGORY_2)).collect(toList()))
            .hasSize(8);
      Assertions.assertThat(result.stream().filter(q -> q.getSubject().equals(CATEGORY_3)).collect(toList()))
            .hasSize(10);
   }

   @Test
   public void givenAnExamCDAKAndAListOfQuestionsWithMissingCategory_whenGenerateExam_ThenListOfQuestionsFilteredAndListHasEnoughQuestionRepeated() {
      Exam exam = new Exam();
      List<Category> categories = new ArrayList<>(getListCategories());
      categories.add(new Category(CATEGORY_4, 28));
      exam.setCategories(categories);
      exam.setNumQuestions(20);
      List<Question> result = exam.generateExam(getListQuestions(8));
      Assertions.assertThat(result).hasSize(20);
      Assertions.assertThat(result.stream().filter(q -> q.getSubject().equals(CATEGORY_1)).collect(toList()))
            .hasSize(2);
      Assertions.assertThat(result.stream().filter(q -> q.getSubject().equals(CATEGORY_2)).collect(toList()))
            .hasSize(8);
      Assertions.assertThat(result.stream().filter(q -> q.getSubject().equals(CATEGORY_3)).collect(toList()))
            .hasSize(10);
   }

   private List<Question> getListQuestions(int numQuestionPerCategories) {
      return IntStream.range(0, numQuestionPerCategories * 3).mapToObj(this::createQuestion)
            .collect(Collectors.toList());
   }

   private Question createQuestion(int i) {
      Question question = new Question();
      question.setQuestion(faker.lorem().sentence(6));
      question.setAnswer(faker.lorem().sentence(3));
      question.setSubject(getCategory(i % 3));
      return question;
   }

   private String getCategory(int numCategory) {
      switch (numCategory) {
      case 0: {
         return CATEGORY_1;
      }
      case 1: {
         return CATEGORY_2;
      }
      case 2: {
         return CATEGORY_3;
      }
      default:
         throw new IllegalArgumentException("Unexpected value: " + numCategory);
      }
   }

   private List<Category> getListCategories() {
      return Arrays.asList(new Category[] { new Category(CATEGORY_1, 10), new Category(CATEGORY_2, 40),
            new Category(CATEGORY_3, 50) });
   }

}
