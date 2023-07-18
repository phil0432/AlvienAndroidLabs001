package algonquin.cst2335.phil0432;

import static algonquin.cst2335.phil0432.ChatRoom.mDAO;
import static algonquin.cst2335.phil0432.ChatRoom.messages;
import static algonquin.cst2335.phil0432.ChatRoom.myAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import algonquin.cst2335.phil0432.databinding.ActivityChatRoomBinding;
import algonquin.cst2335.phil0432.databinding.ReceiveMessageBinding;
import algonquin.cst2335.phil0432.databinding.SentMessageBinding;

public class ChatRoom extends AppCompatActivity {

    ChatRoomViewModel chatModel ;
    static ArrayList<ChatMessage> messages;
    ActivityChatRoomBinding binding;
    static RecyclerView.Adapter<MyRowHolder> myAdapter;
    static  ChatMessageDAO mDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);

        MessageDatabase db = Room.databaseBuilder(getApplicationContext(), MessageDatabase.class, "database-name").build();
        mDAO = db.cmDAO();

        messages = chatModel.messages.getValue();
        if(messages == null)
        {
            chatModel.messages.setValue(messages = new ArrayList<>());

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute(() ->
            {
                messages.addAll( mDAO.getAllMessages() ); //Once you get the data from database

                runOnUiThread( () ->  binding.recycleView.setAdapter( myAdapter )); //You can then load the RecyclerView
            });
        }

        chatModel.messages.observe(this, new Observer<ArrayList<ChatMessage>>() {
            @Override
            public void onChanged(ArrayList<ChatMessage> chatMessages) {
                myAdapter.notifyDataSetChanged();
            }
        });

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");

        binding.sendButton.setOnClickListener(click -> {
            String currentDateandTime = sdf.format(new Date());
            messages.add(new ChatMessage(binding.textInput.getText().toString(), currentDateandTime, true));
            myAdapter.notifyItemInserted(messages.size() - 1);
            binding.textInput.setText("");
        });

        binding.receiveButton.setOnClickListener(click -> {
            String currentDateandTime = sdf.format(new Date());
            messages.add(new ChatMessage(binding.textInput.getText().toString(), currentDateandTime, false));
            myAdapter.notifyItemInserted(messages.size() - 1);
            binding.textInput.setText("");
        });

        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));

        binding.recycleView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                if(viewType == 0){
                    SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater(), parent, false);
                    return new MyRowHolder( binding.getRoot());
                }else{
                    ReceiveMessageBinding binding = ReceiveMessageBinding.inflate(getLayoutInflater(), parent, false);
                    return new MyRowHolder( binding.getRoot());
                }
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                ChatMessage obj = messages.get(position);
                holder.messageText.setText(obj.getMessage());
                holder.timeText.setText(obj.getTimeSent());
            }

            @Override
            public int getItemCount() {
                return messages.size();
            }

            @Override
            public int getItemViewType(int position) {
                return messages.get(position).isSentButton() ? 0 : 1;
            }
        });

    }
}

class MyRowHolder extends RecyclerView.ViewHolder {
    TextView messageText;
    TextView timeText;
    ImageView senderImage; // Update this with your image view ID

    public MyRowHolder(@NonNull View itemView) {
        super(itemView);

        itemView.setOnClickListener(clk ->{

            int position = getAbsoluteAdapterPosition();
            AlertDialog.Builder builder = new AlertDialog.Builder( itemView.getContext() );
            builder.setMessage("Do you want to delete the message: " + messageText.getText())
                    .setTitle("Question: ")
                    .setNegativeButton("No",(dialog, cl) -> {})
                    .setPositiveButton("Yes",(dialogI, cl) -> {
                        ChatMessage m = messages.get(position);
                        messages.remove(position);
                        myAdapter.notifyItemRemoved(position);

                        Executor thread = Executors.newSingleThreadExecutor();
                        thread.execute(() -> {
                            mDAO.deleteMessage(m);

                        });
                        Snackbar.make(messageText, "You deleted message #"+ position, Snackbar.LENGTH_LONG)
                                .setAction("Undo",click ->{
                                    messages.add(position, m);
                                    myAdapter.notifyItemInserted(position);
                                    Executor thread1 = Executors.newSingleThreadExecutor();
                                    thread1.execute(() -> {
                                        mDAO.insertMessage(m);
                                    });

                                })
                                .show();




                    })
                    .create().show();
        });
        messageText = itemView.findViewById(R.id.messageText);
        timeText = itemView.findViewById(R.id.timeText);
        senderImage = itemView.findViewById(R.id.senderImage); // Update this with your image view ID
    }
}

