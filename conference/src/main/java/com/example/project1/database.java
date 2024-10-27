package com.example.project1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class database {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/javaConference";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void insertConference(Conference conference) throws SQLException {
        String query = "INSERT INTO Conference (confoName, topic, place, startDate, endDate, applyDateStart, applyDateEnd, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, conference.getConfoName());
            preparedStatement.setString(2, conference.getTopic());
            preparedStatement.setString(3, conference.getPlace());
            preparedStatement.setDate(4, new java.sql.Date(conference.getStartDate().getTime()));
            preparedStatement.setDate(5, new java.sql.Date(conference.getEndDate().getTime()));
            preparedStatement.setDate(6, new java.sql.Date(conference.getApplyDateStart().getTime()));
            preparedStatement.setDate(7, new java.sql.Date(conference.getApplyDateFinish().getTime()));
            preparedStatement.setDouble(8, conference.getPrice());

            preparedStatement.executeUpdate();
        }
    }
    public static List<Conference> getAllConferences() throws SQLException {
        List<Conference> conferences = new ArrayList<>();
        String query = "SELECT * FROM Conference";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String confoName = resultSet.getString("confoName");
                String topic = resultSet.getString("topic");
                String place = resultSet.getString("place");
                Date startDate = resultSet.getDate("startDate");
                Date endDate = resultSet.getDate("endDate");
                Date applyDateStart = resultSet.getDate("applyDateStart");
                Date applyDateEnd = resultSet.getDate("applyDateEnd");
                double price = resultSet.getDouble("price");

                Conference conference = new Conference(id, confoName, topic, startDate, endDate, applyDateStart, applyDateEnd, place, price);
                conferences.add(conference);
            }
        }

        return conferences;
    }
    public static Conference getConferenceById(int conferenceId) throws SQLException {
        String query = "SELECT * FROM Conference WHERE id = ?";
        Conference conference = null;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, conferenceId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String confoName = resultSet.getString("confoName");
                    String topic = resultSet.getString("topic");
                    String place = resultSet.getString("place");
                    Date startDate = resultSet.getDate("startDate");
                    Date endDate = resultSet.getDate("endDate");
                    Date applyDateStart = resultSet.getDate("applyDateStart");
                    Date applyDateEnd = resultSet.getDate("applyDateEnd");
                    double price = resultSet.getDouble("price");

                    conference = new Conference(conferenceId, confoName, topic, startDate, endDate, applyDateStart, applyDateEnd, place, price);
                }
            }
        }

        return conference;
    }

    public static boolean insertParticipant(String name, String institution, String email, String password) {
        String query = "INSERT INTO Participant (name, institution, email, password) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, institution);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean insertReviewer(String name, String institution, String email, String password) {
        String query = "INSERT INTO Reviewer (name, institution, email, password) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, institution);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static String checkUserType(String email, String password) throws SQLException {
        String queryParticipant = "SELECT * FROM Participant WHERE email = ? AND password = ?";
        String queryReviewer = "SELECT * FROM Reviewer WHERE email = ? AND password = ?";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement participantStatement = connection.prepareStatement(queryParticipant);
             PreparedStatement reviewerStatement = connection.prepareStatement(queryReviewer)) {

            participantStatement.setString(1, email);
            participantStatement.setString(2, password);

            reviewerStatement.setString(1, email);
            reviewerStatement.setString(2, password);

            try (ResultSet participantResult = participantStatement.executeQuery()) {
                if (participantResult.next()) {
                    return "Participant";
                }
            }

            try (ResultSet reviewerResult = reviewerStatement.executeQuery()) {
                if (reviewerResult.next()) {
                    return "Reviewer";
                }
            }
        }

        return "Unknown";
    }
    public static List<Conference> getAllConferences1() throws SQLException {
        List<Conference> conferences = new ArrayList<>();
        String query = "SELECT * FROM Conference";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String confoName = resultSet.getString("confoName");
                String topic = resultSet.getString("topic");
                String country = resultSet.getString("country");
                Date startDate = resultSet.getDate("startDate");
                Date endDate = resultSet.getDate("endDate");
                Date applyDateStart = resultSet.getDate("applyDateStart");
                Date applyDateEnd = resultSet.getDate("applyDateEnd");
                double price = resultSet.getDouble("price");

                Conference conference = new Conference(0, confoName, topic, startDate, endDate, applyDateStart, applyDateEnd, country, price);
                conferences.add(conference);
            }
        }

        return conferences;
    }
    public static int getParticipantIdByEmail(String email) {
        String query = "SELECT id FROM Participant WHERE email = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return a default value if no matching participant is found or an exception occurs
        return -1;
    }
    public static int getreviwerIdByEmail(String email) {
        String query = "SELECT id FROM Reviewer WHERE email = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
    public static void insertPDFFile(int participantId, int conferenceId, String fileName, File file) throws SQLException {
        String query = "INSERT INTO Article (name, pdfFile, idParticipant, idConference) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, fileName);
            preparedStatement.setBlob(2, new FileInputStream(file));
            preparedStatement.setInt(3, participantId);
            preparedStatement.setInt(4, conferenceId);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("PDF file inserted successfully.");
            } else {
                System.out.println("Failed to insert PDF file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Article> getAllArticlesWithParticipants() throws SQLException {
        List<Article> articles = new ArrayList<>();
        String query = "SELECT A.id, A.name, A.pdfFile, A.idParticipant, A.idConference, A.status, P.name AS participantName FROM Article A JOIN Participant P ON A.idParticipant = P.id";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Blob pdfFile = resultSet.getBlob("pdfFile");
                int idParticipant = resultSet.getInt("idParticipant");
                int idConference = resultSet.getInt("idConference");
                String status = resultSet.getString("status");

                Article article = new Article(id, name, pdfFile, idParticipant, idConference, status);
                articles.add(article);
            }
        }

        return articles;
    }

    public static String getEmailByParticipantId(int participantId) {
        String query = "SELECT email FROM Participant WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, participantId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("email");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return null if no matching participant is found or an exception occurs
        return null;
    }
    public static List<String> getAllReviewerEmails() {
        List<String> reviewerEmails = new ArrayList<>();
        String query = "SELECT email FROM Reviewer";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                reviewerEmails.add(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviewerEmails;
    }
    public static void updateArticleReviewerAndStatus(int articleId, Integer reviewerId) throws SQLException {
        String query = "UPDATE Article SET idReviewer = ?, status = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setObject(1, reviewerId); // Set reviewerId to null if reviewer is not assigned
            preparedStatement.setString(2, "UA"); // Set status to "UA" for under assignment
            preparedStatement.setInt(3, articleId);

            preparedStatement.executeUpdate();
        }
    }
    public static int getReviewerIdByEmail(String email) throws SQLException {
        String query = "SELECT id FROM Reviewer WHERE email = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        // Return a default value if no matching reviewer is found or an exception occurs
        return -1;
    }
    public static List<Article> getArticlesByReviewerId(int reviewerId) throws SQLException {
        List<Article> articles = new ArrayList<>();
        String query = "SELECT A.id, A.name, A.pdfFile, A.idParticipant, A.idConference, A.status, P.name AS participantName " +
                "FROM Article A " +
                "JOIN Participant P ON A.idParticipant = P.id " +
                "WHERE A.idReviewer = ? ";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, reviewerId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    Blob pdfFile = resultSet.getBlob("pdfFile");
                    int idParticipant = resultSet.getInt("idParticipant");
                    int idConference = resultSet.getInt("idConference");
                    String status = resultSet.getString("status");

                    Article article = new Article(id, name, pdfFile, idParticipant, idConference, status);
                    articles.add(article);
                }
            }
        }

        return articles;
    }
    public static void updateArticleStatus(int articleId, String newStatus) throws SQLException {
        String query = "UPDATE Article SET status = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newStatus);
            preparedStatement.setInt(2, articleId);

            preparedStatement.executeUpdate();
        }
    }

    public static void deleteConference(int conferenceId) throws SQLException {
        String query = "DELETE FROM Conference WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, conferenceId);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Conference deleted successfully.");
            } else {
                System.out.println("No conference found with the given ID.");
            }
        }
    }
    public static boolean addKeySpeaker(String speakerName, String speakerCountry, String speakerInstitution, String speakerTitle, String selectedConference) throws SQLException {
        String query = "INSERT INTO KeySpeaker (name, country, Institution, Title, conferenceName) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, speakerName);
            preparedStatement.setString(2, speakerCountry);
            preparedStatement.setString(3, speakerInstitution);
            preparedStatement.setString(4, speakerTitle);
            preparedStatement.setString(5, selectedConference);

            preparedStatement.executeUpdate();
        }
        return false;
    }
    public static List<KeySpeaker> getAllKeySpeakers() throws SQLException {
        List<KeySpeaker> keySpeakers = new ArrayList<>();
        String query = "SELECT * FROM KeySpeaker";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                String institution = resultSet.getString("institution");
                String title = resultSet.getString("title");
                String conference = resultSet.getString("conference");

                KeySpeaker keySpeaker = new KeySpeaker(id, name, country, institution, title, conference);
                keySpeakers.add(keySpeaker);
            }
        }

        return keySpeakers;
    }
    public static List<String> getAllConferenceTopics() throws SQLException {
        List<String> topics = new ArrayList<>();
        String query = "SELECT DISTINCT topic FROM Conference";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String topic = resultSet.getString("topic");
                topics.add(topic);
            }
        }

        return topics;
    }



}


