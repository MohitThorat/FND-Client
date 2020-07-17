package com.example.fndclient;

public class FakeText {
    private String fake_text;
    private String Description;
    private String Feedback_1;
    private String Feedback_2;


    public FakeText(String fake_text) {
        this.fake_text = fake_text;
    }


    public FakeText(String fake_text, String description, String feedback_1) {
        this.fake_text = fake_text;
        Description = description;
        Feedback_1 = feedback_1;
    }

    public FakeText(String fake_text, String description) {
        this.fake_text = fake_text;
        Description = description;
    }

    public String getFeedback_1() {
        return Feedback_1;
    }

    public String getFeedback_2() {
        return Feedback_2;
    }

    public String getfake_text() {
        return fake_text;
    }

    public String getDescription() {
        return Description;
    }
}
