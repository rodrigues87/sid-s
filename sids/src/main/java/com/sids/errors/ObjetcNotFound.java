package com.sids.errors;

import lombok.Data;

@Data
public class ObjetcNotFound extends ErrorDetail {


    private ObjetcNotFound() {

    }

    public static final class Builder {
        private String title;
        private int status;
        private String detail;
        private long timestamp;
        private String developerMessage;

        private Builder() {

        }

        public static Builder newBuilder() {
            return new Builder();
        }


        public ObjetcNotFound.Builder title(String title) {
            this.title = title;
            return this;
        }

        public ObjetcNotFound.Builder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ObjetcNotFound.Builder timestamp(Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ObjetcNotFound.Builder detail(String detail) {
            this.detail = detail;
            return this;
        }


        public ObjetcNotFound.Builder status(int status) {
            this.status = status;
            return this;
        }

        public ObjetcNotFound build() {

            ObjetcNotFound objetctNotFound = new ObjetcNotFound();

            objetctNotFound.setDeveloperMessage(developerMessage);
            objetctNotFound.setTitle(title);
            objetctNotFound.setDetail(detail);
            objetctNotFound.setTimestamp(timestamp);
            objetctNotFound.setStatus(status);


            return objetctNotFound;
        }


    }
}
