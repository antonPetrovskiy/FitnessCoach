package fitness.tosch.com.fitness.sql;

import java.util.List;

public interface IDatabaseHandler {
    public void addClient(Client client);
    public Client getClient(int id);
    public List<Client> getAllClient();
    public int getClientCount();
    public int updateClient(Client client);
    public void deleteClient(Client client);
    public Client findClient(String s);

    public List<String> getHistory();
    public void addToHistory(String s, String s1);
    public void deleteFromHistory(String s1, String s2);

    public void setProfile(String s, String s1);
    public void editProfile(String s, String s1);
    public String getName();
    public String getPhoto();

}
