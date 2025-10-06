    package org.example;

    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;

    @Entity
    public class BuddyInfo {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String name;
        private int number;
        private String home;
        private String address;

        public BuddyInfo() {}

        public BuddyInfo(String name, int number, String home, String address) {
            this.name = name;
            this.number = number;
            this.home = home;
            this.address = address;
        }

        public String getName() {
            return name;
        }
        public int getNumber() {
            return number;
        }
        public String getHome() {
            return home;
        }
        public Long getId() {
            return id != null ? id : -1;
        }

        public String getAddress() {
            return address;
        }

    }
