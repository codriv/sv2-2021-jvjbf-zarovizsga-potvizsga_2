package message;

import java.util.Optional;

public class MessageService {

    private UserRepository userRepository;
    private MessageRepository messageRepository;

    public MessageService(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    public void registerUser(String username) {
        if (userRepository.findUserByName(username).isEmpty()) {
            userRepository.insertUser(username);
        } else {
            throw new IllegalArgumentException("Username is already taken: " + username);
        }
    }

    void sendMessage(User sender, User receiver, String message) {
        Optional<User> senderFound = userRepository.findUserByName(sender.getUsername());
        Optional<User> receiverFound = userRepository.findUserByName(receiver.getUsername());
        if (senderFound.isPresent() && receiverFound.isPresent()) {
            messageRepository.sendMessage(senderFound.get(), receiverFound.get(), message);
        }
    }
}
