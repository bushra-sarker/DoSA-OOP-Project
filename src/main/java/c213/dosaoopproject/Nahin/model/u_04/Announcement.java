package c213.dosaoopproject.Nahin.model.u_04;

import java.io.Serializable;
import java.time.LocalDate;

public class Announcement implements Serializable{

        private final int announcementId;
        private final String title;
        private final String category;
        private final String details;
        private final LocalDate publishDate;

        public Announcement(int announcementId, String title, String category, String details, LocalDate publishDate) {
            this.announcementId = announcementId;
            this.title = title;
            this.category = category;
            this.details = details;
            this.publishDate = publishDate;
        }

        public int getAnnouncementId() {
            return announcementId;
        }

        public String getTitle() {
            return title;
        }

        public String getCategory() {
            return category;
        }

        public String getDetails() {
            return details;
        }

        public LocalDate getPublishDate() {
            return publishDate;
        }

        @Override
        public String toString() {
            return "Announcement{" +
                    "announcementId=" + announcementId +
                    ", title='" + title + '\'' +
                    ", category='" + category + '\'' +
                    ", details='" + details + '\'' +
                    ", publishDate=" + publishDate +
                    '}';
        }

        public boolean validateInfo(){
            return title != null && !title.trim().isEmpty() &&
                    category != null && !category.trim().isEmpty() &&
                    details != null && !details.trim().isEmpty() &&
                    publishDate != null;
        }
}
