package com.cs4md.quizappdu;

public class Question {
    //initialize variables
    private String qText;
    private boolean correctAnswer;
    private String hintURL;
    private int qSound;

    public Question() {
        qText = "";
        correctAnswer = false;
        hintURL = "";
        qSound = 0;
    }

    public Question(String qText, boolean correctAnswer, String hintURL, int qSound) {
        this.qText = qText;
        this.correctAnswer = correctAnswer;
        this.hintURL = hintURL;
        this.qSound= qSound;
    }

    //Getters and Setters
    public String getqText() {
        return qText;
    }
    public String getHintURL(){
        return hintURL;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public int getqSound(){return qSound;}

    public void setqText(String qText) {
        this.qText = qText;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setHintURL(String hintURL){
        this.hintURL = hintURL;
    }

    public void setqSound(int qSound){ this.qSound = qSound;}

    @Override
    public String toString() {
        return "Question{" +
                "qText='" + qText + '\'' +
                ", correctAnswer=" + correctAnswer + '\'' + ", hintURL=" + hintURL +
                '}';
    }
}
