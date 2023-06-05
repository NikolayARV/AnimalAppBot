package com.example.demoanimalbot.model;

/**
 * статус необходим, чтобы понимать, где находится животное:
 * в приюте - SHELTER,
 * на испытательном сроке у усыновителя - PROBATION,
 * или уже усыновлено после успешного прохождения испытательного срока - HOME
 */
public enum Status {
    SHELTER ("В приюте"),
    PROBATION ("Испытательный срок"),
    HOME("В новом доме");

   private String title;

    public String getTitle() {
        return title;
    }

    Status(String title) {
        this.title = title;
    }
}
