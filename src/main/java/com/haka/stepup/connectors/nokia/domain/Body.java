package com.haka.stepup.connectors.nokia.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Body {

    @SerializedName("activities")
    @Expose
    private List<Activity> activities = null;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("steps")
    @Expose
    private Integer steps;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("elevation")
    @Expose
    private Integer elevation;
    @SerializedName("soft")
    @Expose
    private Integer soft;
    @SerializedName("moderate")
    @Expose
    private Integer moderate;
    @SerializedName("intense")
    @Expose
    private Integer intense;
    @SerializedName("calories")
    @Expose
    private Double calories;
    @SerializedName("totalcalories")
    @Expose
    private Double totalcalories;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("brand")
    @Expose
    private Integer brand;
    @SerializedName("is_tracker")
    @Expose
    private Boolean isTracker;
    @SerializedName("more")
    @Expose
    private Boolean more;
    @SerializedName("offset")
    @Expose
    private Integer offset;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getElevation() {
        return elevation;
    }

    public void setElevation(Integer elevation) {
        this.elevation = elevation;
    }

    public Integer getSoft() {
        return soft;
    }

    public void setSoft(Integer soft) {
        this.soft = soft;
    }

    public Integer getModerate() {
        return moderate;
    }

    public void setModerate(Integer moderate) {
        this.moderate = moderate;
    }

    public Integer getIntense() {
        return intense;
    }

    public void setIntense(Integer intense) {
        this.intense = intense;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getTotalcalories() {
        return totalcalories;
    }

    public void setTotalcalories(Double totalcalories) {
        this.totalcalories = totalcalories;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Integer getBrand() {
        return brand;
    }

    public void setBrand(Integer brand) {
        this.brand = brand;
    }

    public Boolean getIsTracker() {
        return isTracker;
    }

    public void setIsTracker(Boolean isTracker) {
        this.isTracker = isTracker;
    }

    public Boolean getMore() {
        return more;
    }

    public void setMore(Boolean more) {
        this.more = more;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public String toString() {
        return "Body{" +
                "date='" + date + '\'' +
                ", steps=" + steps +
                ", distance=" + distance +
                ", elevation=" + elevation +
                ", soft=" + soft +
                ", moderate=" + moderate +
                ", intense=" + intense +
                ", calories=" + calories +
                ", totalcalories=" + totalcalories +
                ", timezone='" + timezone + '\'' +
                ", brand=" + brand +
                ", isTracker=" + isTracker +
                ", more=" + more +
                ", offset=" + offset +
                '}';
    }
}