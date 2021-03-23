package com.anikrakib.blooddonation.Model;

import com.google.firebase.Timestamp;

public class CampaignModel {
    private String campaignId;
    private String campaignTitle;
    private String campaignHost;
    private String campaignDate;
    private String campaignImage;
    private Timestamp campaignTime;

    public CampaignModel() {
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignTitle() {
        return campaignTitle;
    }

    public void setCampaignTitle(String campaignTitle) {
        this.campaignTitle = campaignTitle;
    }

    public String getCampaignHost() {
        return campaignHost;
    }

    public void setCampaignHost(String campaignHost) {
        this.campaignHost = campaignHost;
    }

    public String getCampaignDate() {
        return campaignDate;
    }

    public void setCampaignDate(String campaignDate) {
        this.campaignDate = campaignDate;
    }

    public String getCampaignImage() {
        return campaignImage;
    }

    public void setCampaignImage(String campaignImage) {
        this.campaignImage = campaignImage;
    }

    public Timestamp getCampaignTime() {
        return campaignTime;
    }

    public void setCampaignTime(Timestamp campaignTime) {
        this.campaignTime = campaignTime;
    }
}
