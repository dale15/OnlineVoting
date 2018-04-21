package com.projects.onlinevoting.Models;

import java.util.List;

public class CandidateModel {

    private List<CandInfoModel> result;

    public static class CandInfoModel {

        private String candidate_name;
        private String candidate_image;
        private String total_votes;

        public String getCandidate_name() {
            return candidate_name;
        }

        public void setCandidate_name(String candidate_name) {
            this.candidate_name = candidate_name;
        }

        public String getCandidate_image() {
            return candidate_image;
        }

        public void setCandidate_image(String candidate_image) {
            this.candidate_image = candidate_image;
        }

        public String getTotal_votes() {
            return total_votes;
        }

        public void setTotal_votes(String total_votes) {
            this.total_votes = total_votes;
        }

    }

    public List<CandInfoModel> getResult() {
        return result;
    }

    public void setResult(List<CandInfoModel> result) {
        this.result = result;
    }
}
