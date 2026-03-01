package com.example.tickets;

import java.util.ArrayList;
import java.util.List;

/**
 * INTENTION: A ticket should be an immutable record-like object.
 *
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - mutable fields
 * - multiple constructors
 * - public setters
 * - tags list can be modified from outside
 * - validation is scattered elsewhere
 *
 * TODO (student): refactor to immutable + Builder.
 */
public class IncidentTicket {

    private final String id;
    private final String reporterEmail;
    private final String title;

    private final String description;
    private final String priority;       // LOW, MEDIUM, HIGH, CRITICAL
    private final List<String> tags;     // mutable leak
    private final String assigneeEmail;
    private final boolean customerVisible;
    private final Integer slaMinutes;    // optional
    private final String source;         // e.g. "CLI", "WEBHOOK", "EMAIL"

    private IncidentTicket(String id, String reporterEmail, String title, String description, String priority, List<String> tags, String assigneeEmail, boolean customerVisible, Integer slaMinutes, String source) {
        this.id = id;
        this.reporterEmail = reporterEmail;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.tags = List.copyOf(tags);  // immutable
        this.assigneeEmail = assigneeEmail;
        this.customerVisible = customerVisible;
        this.slaMinutes = slaMinutes;
        this.source = source;
    }

    // Getters
    public String getId() { return this.id; }
    public String getReporterEmail() { return this.reporterEmail; }
    public String getTitle() { return this.title; }
    public String getDescription() { return this.description; }
    public String getPriority() { return this.priority; }
    public List<String> getTags() { return List.copyOf(this.tags); } // BROKEN: leaks internal list
    public String getAssigneeEmail() { return this.assigneeEmail; }
    public boolean isCustomerVisible() { return this.customerVisible; }
    public Integer getSlaMinutes() { return this.slaMinutes; }
    public String getSource() { return this.source; }


    public static class Builder{
        private final String id;
        private final String reporterEmail;
        private final String title;

        private String description;
        private String priority;       // LOW, MEDIUM, HIGH, CRITICAL
        private List<String> tags;     // mutable leak
        private String assigneeEmail;
        private boolean customerVisible;
        private Integer slaMinutes;    // optional
        private String source;

        private Builder(String id, String reporterEmail, String title) {
            this.id = id;
            this.reporterEmail = reporterEmail;
            this.title = title;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }
        public Builder priority(String priority){
            this.priority = priority;
            return this;
        }
        public Builder tags(List<String> tags){
            if(tags == null) this.tags = new ArrayList<>();
            else this.tags = new ArrayList<>(tags); //mutable
            return this;
        }
        public Builder assigneeEmail(String assigneeEmail){
            this.assigneeEmail = assigneeEmail;
            return this;
        }
        public Builder customerVisible(boolean customerVisible){
            this.customerVisible = customerVisible;
            return this;
        }
        public Builder slaMinutes(Integer slaMinutes){
            this.slaMinutes = slaMinutes;
            return this;
        }
        public Builder source(String source){
            this.source = source;
            return this;
        }

        public IncidentTicket build(){

            Validation.requireTicketId(this.id);
            Validation.requireEmail(this.reporterEmail, "reporterEmail");

            Validation.requireNonBlank(this.title, "title");
            Validation.requireMaxLen(this.title, 80, "title");

            Validation.requireOneOf(this.priority, "priority", "LOW", "MEDIUM", "HIGH", "CRITICAL");
            Validation.requireRange(this.slaMinutes, 5, 7200, "slaMinutes");

            if(assigneeEmail != null) { //keeps assignee email optional
                Validation.requireEmail(assigneeEmail, "assigneeEmail");
            }

            return new IncidentTicket(this.id, this.reporterEmail, this.title, this.description, this.priority, this.tags, this.assigneeEmail, this.customerVisible, this.slaMinutes, this.source);
        }

    }

    public static Builder builder(String id, String reporterEmail, String title) {
        return new Builder(id, reporterEmail, title);
    }

    public Builder toBuilder() {
        return new Builder(this.id, this.reporterEmail, this.title)
                .description(this.description)
                .priority(this.priority)
                .tags(this.tags)
                .assigneeEmail(this.assigneeEmail)
                .customerVisible(this.customerVisible)
                .slaMinutes(this.slaMinutes)
                .source(this.source);
    }



    @Override
    public String toString() {
        return "IncidentTicket{" +
                "id='" + id + '\'' +
                ", reporterEmail='" + reporterEmail + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", tags=" + tags +
                ", assigneeEmail='" + assigneeEmail + '\'' +
                ", customerVisible=" + customerVisible +
                ", slaMinutes=" + slaMinutes +
                ", source='" + source + '\'' +
                '}';
    }
}
