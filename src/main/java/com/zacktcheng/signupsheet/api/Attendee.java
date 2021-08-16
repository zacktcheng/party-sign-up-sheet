package com.zacktcheng.signupsheet.api;

public class Attendee {

    private int id;
    private String name;
    private String quantity = "1";
    private String bringing;
    private String mobile;
    private String note;
    private String mobilePreview;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getBringing() {
        return bringing;
    }
    public void setBringing(String bringing) {
        this.bringing = bringing;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getMobilePreview() {
        return mobilePreview;
    }
    public void setMobilePreview(String mobilePreview) {
        this.mobilePreview = mobilePreview;
    }
    @Override
    public String toString() {
        return "Attendee [id=" + id + ", name=" + name + ", quantity=" + quantity + ", bringing=" + bringing
                + ", mobile=" + mobile + ", note=" + note + "]";
    }
}
