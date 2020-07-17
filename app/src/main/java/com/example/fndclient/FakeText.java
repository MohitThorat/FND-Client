package com.example.fndclient;

public class FakeText {


    private String fake_text;
    private String Description;
    private String feedback_one;
    private String feedback_two;


    public FakeText(String fake_text) {
        this.fake_text = fake_text;
    }


    public FakeText(String fake_text, String feedback_1) {
        this.fake_text = fake_text;
        this.feedback_one = feedback_1;
    }

    public FakeText(String fake_text, String feedback_1, String feedback_2) {
        this.fake_text = fake_text;
        this.feedback_two = feedback_2;
        this.feedback_one = feedback_1;
    }

    public void setFake_text(String fake_text) {
        this.fake_text = fake_text;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setFeedback_1(String feedback_1) {
        this.feedback_one = feedback_1;
    }

    public void setFeedback_2(String feedback_2) {
        this.feedback_two = feedback_2;
    }




    public String getFeedback_1() {
        return feedback_one;
    }

    public String getFeedback_2() {
        return feedback_two;
    }

    public String getfake_text() {
        return fake_text;
    }

    public String getDescription() {
        return Description;
    }
}
