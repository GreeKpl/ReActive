package pl.edu.agh.inz.reactive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import static pl.edu.agh.inz.reactive.R.color;


public class AdminActivity extends Activity implements OnClickListener {

	private ListView list;
	private EditText loginUser, nameUser, surnameUser;
	private TextView information;
	private ArrayList<String> listItems = new ArrayList<String>();
	private ArrayAdapter<String> adapter;
	private Map<String, User> usersMap = new HashMap<String, User>();
	private View bAddUser, bDeleteUser, bLoginUser;
	private Intent target;
	private boolean newfl = true;

	private DatabaseManager db;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);

		target = new Intent(this, MainMenuActivity.class);
		
		loginUser = (EditText) this.findViewById(R.id.etLogin);
		nameUser = (EditText) this.findViewById(R.id.etName);
		surnameUser = (EditText) this.findViewById(R.id.etSurname);
		bAddUser = this.findViewById(R.id.buttonAdd);		
		bDeleteUser = this.findViewById(R.id.buttonDelete);
		bLoginUser = this.findViewById(R.id.buttonLogin);
		list = (ListView) this.findViewById(R.id.lvUsers);
		information = (TextView) this.findViewById(R.id.tvInformation);
		
		bAddUser.setOnClickListener(this);
		bDeleteUser.setOnClickListener(this);
		bLoginUser.setOnClickListener(this);
		loginUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
		    @Override
		    public void onFocusChange(View v, boolean hasFocus) {
		        if(hasFocus) {
		            activeText(false);
		        }
		    }
		});
		nameUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
		    @Override
		    public void onFocusChange(View v, boolean hasFocus) {
		        if(hasFocus) {
		            activeText(false);
		        }
		    }
		});
		surnameUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
		    @Override
		    public void onFocusChange(View v, boolean hasFocus) {
		        if(hasFocus) {
		            activeText(false);
		        }
		    }
		});
		list.setOnItemClickListener(list.getOnItemClickListener());
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				final String item = (String) parent.getItemAtPosition(position);
				selected(item);
				activeText(true);
			}
		});
		
		db = new DatabaseManager(this);
        db.open();
		if (db.getAllUsersCollection().isEmpty()) {
			User guest = new User(getString(R.string.default_user_login), getString(R.string.default_user_name), getString(R.string.default_user_surname));
			db.insertUser(guest);
		}
		for (User u: db.getAllUsersCollection()) {
			listItems.add(u.getLogin());
			usersMap.put(u.getLogin(), u);
			adapter.add(u.getLogin());
			list.setAdapter(adapter);
		}
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.buttonAdd:
			addUser(view);
			break;
		case R.id.buttonDelete:
			removeUser(view);
			break;
		case R.id.buttonLogin:
			loginUser(view);
			break;
		}
	}

	private void addUser(View view) {
		
		if ((loginUser.length() != 0) && (nameUser.length() != 0) && (surnameUser.length() != 0)) {
            if (db.getUser(loginUser.getText().toString()) == null) {
                User user = new User();
                user.setLogin(loginUser.getText().toString());
                user.setName(nameUser.getText().toString());
                user.setSurname(surnameUser.getText().toString());
                usersMap.put(user.getLogin(), user);
                adapter.add(user.getLogin());
                list.setAdapter(adapter);
                cleanEditText();

                showAcceptInformation(getString(R.string.add_user_information) + " " + user.getLogin());

                db.insertUser(user);
            } else {
                showWarningInformation(getString(R.string.busy_login_information));
            }
		} else {
			showWarningInformation(getString(R.string.empty_fields_information));
		}
		

	}
	
	private void removeUser(View view) {
		User user = new User();
		user.setLogin(loginUser.getText().toString());
		user.setName(nameUser.getText().toString());
		user.setSurname(surnameUser.getText().toString());
		
		usersMap.remove(user.getLogin());
		adapter.remove(user.getLogin());
		list.setAdapter(adapter);
		showAcceptInformation(getString(R.string.delete_user_information) + " " + user.getLogin());
		cleanEditText();
		
		db.deleteUser(user);
	}
	
	private void selected(String login) {
		User user = usersMap.get(login);
		loginUser.setText(user.getLogin());
		nameUser.setText(user.getName());
		surnameUser.setText(user.getSurname());
		
		activeText(true);

        cleanInformation();
	}
	
	private void loginUser(View view) {
		if ((loginUser.length() != 0) && (nameUser.length() != 0) && (surnameUser.length() != 0)) {
			db.setActiveUser(loginUser.getText().toString());

            db.close();

			finish();
			startActivity(target);
		} else {
			showWarningInformation(getString(R.string.choose_user_information));
		}
	}

	private void cleanEditText() {
		loginUser.setText("");
		nameUser.setText("");
		surnameUser.setText("");
	}

	private void activeText(boolean fl) {
		if (fl) {
			bAddUser.setVisibility(View.INVISIBLE);
			bLoginUser.setVisibility(View.VISIBLE);
			bDeleteUser.setVisibility(View.VISIBLE);
			newfl = true;
		} else {
			if (newfl) {
				cleanEditText();
				newfl = false;
			}
			bAddUser.setVisibility(View.VISIBLE);
			bLoginUser.setVisibility(View.INVISIBLE);
			bDeleteUser.setVisibility(View.INVISIBLE);
		}
	}

    private void showAcceptInformation(String text) {
        information.setText(text);
        information.setTextColor(color.txtAccept);
    }

    private void showWarningInformation(String text) {
        information.setText(text);
        information.setTextColor(color.txtWarning);
    }

    private void cleanInformation() {
        information.setText("");
    }


}