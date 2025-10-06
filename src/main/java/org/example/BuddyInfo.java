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

        public BuddyInfo() {}

        public BuddyInfo(String name, int number, String home) {
            this.name = name;
            this.number = number;
            this.home = home;
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
        public long getId() {return id;}

    }
