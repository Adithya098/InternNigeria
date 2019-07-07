package com.werpindia.internnigeria.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.werpindia.internnigeria.models.Chat;
import com.werpindia.internnigeria.models.FirebaseResponse;
import com.werpindia.internnigeria.models.Message;

import java.util.ArrayList;

public class ChatRepository {

    private final String CHAT_COLLECTION_NAME = "Chats";
    private final String CHAT_MESSAGES_COLLECTION_NAME = "Messages";
    private final FirebaseFirestore store = FirebaseFirestore.getInstance();

    public LiveData<FirebaseResponse> startChat(Chat chat) {
        MutableLiveData<FirebaseResponse> result = new MutableLiveData<>();

        store.collection(CHAT_COLLECTION_NAME).document(chat.getId()).set(chat).addOnCompleteListener(task -> {
            if (task.isSuccessful()) result.setValue(new FirebaseResponse("Done", null));
            else result.setValue(new FirebaseResponse(null, task.getException()));
        });

        return result;
    }

    public LiveData<FirebaseResponse> sendMessage(Message message) {
        MutableLiveData<FirebaseResponse> result = new MutableLiveData<>();

        store.collection(CHAT_COLLECTION_NAME)
                .document(message.getChatId())
                .collection(CHAT_MESSAGES_COLLECTION_NAME).add(message)
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        result.setValue(new FirebaseResponse(null, new Exception("Error Sending Message")));
                    }
                });
        return result;
    }

    public LiveData<FirebaseResponse> getChatMessages(String chatId) {
        MutableLiveData<FirebaseResponse> result = new MutableLiveData<>();
        store.collection(CHAT_COLLECTION_NAME).document(chatId)
                .collection(CHAT_MESSAGES_COLLECTION_NAME).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null) {
                    ArrayList<Message> messages = new ArrayList<>();
                    for (DocumentSnapshot snapshot : querySnapshot)
                        messages.add(snapshot.toObject(Message.class));
                    result.setValue(new FirebaseResponse(messages, null));
                } else
                    result.setValue(new FirebaseResponse(null, new Exception("No Messages Found")));
            } else new FirebaseResponse(null, task.getException());
        });
        return result;
    }

    public LiveData<Message> getNewMessages(String chatId) {
        MutableLiveData<Message> result = new MutableLiveData<>();
        store.collection(CHAT_COLLECTION_NAME).document(chatId)
                .collection(CHAT_MESSAGES_COLLECTION_NAME).addSnapshotListener((queryDocumentSnapshots, e) -> {
            if (e == null) {
                if (queryDocumentSnapshots != null) {
                    for (DocumentChange change : queryDocumentSnapshots.getDocumentChanges()) {
                        if (change.getType() == DocumentChange.Type.ADDED) {
                            result.setValue(change.getDocument().toObject(Message.class));
                        }
                    }
                }
            }
        });
        return result;
    }


}
