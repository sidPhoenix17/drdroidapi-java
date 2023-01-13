package org.drdroid.api.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkflowEvent implements java.io.Serializable {

    private Workflow workflow;
    private String timeStamp;
    private String state;
    private List<KeyValue> kvPairs;

    public WorkflowEvent() {
    }

    public WorkflowEvent(Workflow workflow, String timeStamp, String state, List<KeyValue> kvPairs) {
        this.workflow = workflow;
        this.timeStamp = timeStamp;
        this.state = state;
        this.kvPairs = kvPairs;
    }

    @JsonGetter("workflow")
    public Workflow getWorkflow() {
        return this.workflow;
    }

    @JsonSetter("workflow")
    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }

    @JsonGetter("timestamp")
    public String getTimeStamp() {
        return this.timeStamp;
    }

    @JsonSetter("timestamp")
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @JsonGetter("state")
    public String getState() {
        return this.state;
    }

    @JsonSetter("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonGetter("kvs")
    public List<KeyValue> getKvPairs() {
        return this.kvPairs;
    }

    @JsonSetter("kvs")
    public void setKvPairs(List<KeyValue> kvPairs) {
        this.kvPairs = kvPairs;
    }

}
