package fitness.tosch.com.fitness.sql;

public class Client {

    int _id;
    String _photo;
    String _name;
    String _age;
    String _tall;
    String _weight;
    String _days;
    String _info;

    public Client(){
    }

    public Client(int id, String photo, String name, String age, String tall, String weight, String days, String info){
        this._id = id;
        this._photo = photo;
        this._name = name;
        this._age = age;
        this._tall = tall;
        this._weight = weight;
        this._days = days;
        this._info = info;
    }

    public Client(String photo, String name, String age, String tall, String weight, String days, String info){
        this._photo = photo;
        this._name = name;
        this._age = age;
        this._tall = tall;
        this._weight = weight;
        this._days = days;
        this._info = info;
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_photo() {
        return _photo;
    }

    public void set_photo(String _photo) {
        this._photo = _photo;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_age() {
        return _age;
    }

    public void set_age(String _age) {
        this._age = _age;
    }

    public String get_tall() {
        return _tall;
    }

    public void set_tall(String _tall) {
        this._tall = _tall;
    }

    public String get_weight() {
        return _weight;
    }

    public void set_weight(String _weight) {
        this._weight = _weight;
    }

    public String get_days() {
        return _days;
    }

    public void set_days(String _days) {
        this._days = _days;
    }

    public String get_info() {
        return _info;
    }

    public void set_info(String _info) {
        this._info = _info;
    }
}
