
        package com.haka.stepup.connectors.nokia.domain;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

        public class ActivityData {

            @SerializedName("status")
            @Expose
            private Integer status;
            @SerializedName("body")
            @Expose
            private Body body;

            public Integer getStatus() {
                return status;
            }

            public void setStatus(Integer status) {
                this.status = status;
            }

            public Body getBody() {
                return body;
            }

            public void setBody(Body body) {
                this.body = body;
            }

            @Override
            public String toString() {
                return "ActivityData{" +
                        "status=" + status +
                        ", body=" + body +
                        '}';
            }
        }
