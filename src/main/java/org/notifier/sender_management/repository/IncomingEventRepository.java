package org.notifier.sender_management.repository;

//@Repository
public class IncomingEventRepository {

//    private final String INSERT_SQL = "INSERT INTO event_collection(document) VALUES (?::jsonb)";
//    private final String GET_EVENTS_SQL = "SELECT document FROM event_collection";
//
//    private final String FIND_MESSAGES_SQL = "SELECT document " +
//            "FROM event_collection " +
//            "WHERE ((document#>'{publisherId}')=to_json(?)::jsonb) AND " +
//            "((document#>'{channel}')=to_json(?)::jsonb)";
//
//    private final JdbcTemplate jdbcTemplate;
//
//    public IncomingEventRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public Mono<Void> saveEvent(IncomingEvent event) {
//
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            String value = mapper.writer().forType(IncomingEvent.class).writeValueAsString(event);
//            this.jdbcTemplate.update(INSERT_SQL, value);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return Mono.empty();
//    }
//
//    public Flux<IncomingEvent> getEvents() {
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        List<IncomingEvent> documents = this.jdbcTemplate.query(GET_EVENTS_SQL, (resultSet, i) -> {
//            String document = resultSet.getString("document");
//            IncomingEvent incomingEvent = null;
//            try {
//                incomingEvent = mapper.reader()
//                        .forType(IncomingEvent.class).readValue(document);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return incomingEvent;
//        });
//
//        return Flux.fromStream(documents.stream());
//    }
//
//    public Flux<EventMessage> getClientMessages(String publisherId, String channel) {
//        ObjectMapper mapper = new ObjectMapper();
//
//        String[] args = {publisherId, channel};
//        List<EventMessage> documents = this.jdbcTemplate.query(FIND_MESSAGES_SQL, args, (resultSet, i) -> {
//            String document = resultSet.getString("document");
//            IncomingEvent incomingEvent = null;
//            try {
//                incomingEvent = mapper.reader()
//                        .forType(IncomingEvent.class).readValue(document);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return incomingEvent.getMessage();
//        });
//        return Flux.fromStream(documents.stream());
//    }

}
