package com.cs4md.quizappdu;

public class Question {
    //initialize variables
    private String qText;
    private boolean correctAnswer;
    private String hintURL;

    public Question() {
        qText = "";
        correctAnswer = false;
        hintURL = "";
    }

    public Question(String qText, boolean correctAnswer, String hintURL) {
        this.qText = qText;
        this.correctAnswer = correctAnswer;
        this.hintURL = hintURL;
    }

    public String getqText() {
        return qText;
    }
    public String getHintURL(){
        return hintURL;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setqText(String qText) {
        this.qText = qText;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setHintURL(String hintURL){
        this.hintURL = hintURL;
    }

    @Override
    public String toString() {
        return "Question{" +
                "qText='" + qText + '\'' +
                ", correctAnswer=" + correctAnswer + '\'' + ", hintURL=" + hintURL +
                '}';
    }
}
