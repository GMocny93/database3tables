package com.example.grzesiek.database3tables;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    //database verion
    private static final int DATABASE_VERSION = 1;

    //database name
    private static final String DATABASE_NAME = "databaseApp";

    //information tabele name
    private static final String TABLE_INFORMATION = "information";

    //information table columns name
    private static final String INF_ID = "id";
    private static final String INF_NAME = "name";
    private static final String INF_HEIGHT = "height";
    private static final String INF_WEIGHT = "weight";
    private static final String INF_TARGETWEIGHT = "targetWeight";
    private static final String INF_AGE = "age";


    private static final String TABLE_WEIGHT = "weight";

    private static final String WG_ID = "id";
    private static final String WG_YEAR = "year";
    private static final String WG_MONTH = "month";
    private static final String WG_DAY = "day";
    private static final String WG_WEIGHT = "weight";


    private static final String TABLE_CIRCUIT = "circuit";

    private static final String OB_ID = "id";
    private static final String OB_YEAR = "year";
    private static final String OB_MONTH = "month";
    private static final String OB_DAY = "day";
    private static final String OB_CHEST = "chest";
    private static final String OB_WAIST = "waist";



    public DatabaseHandler(MainActivity context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating tables

    @Override
    public void onCreate(SQLiteDatabase db) {
        //information table
        String CREATE_INFROMATION_TABLE = "CREATE TABLE " + TABLE_INFORMATION + "("
                + INF_ID + " INTEGER PRIMARY KEY," + INF_NAME + " TEXT,"
                + INF_HEIGHT + " INT," + INF_WEIGHT + " REAL,"
                + INF_TARGETWEIGHT + " REAL," + INF_AGE + " INT" + ")";

        String CREATE_WEIGHT_TABLE = "CREATE TABLE " + TABLE_WEIGHT + "("
                + WG_ID + " INTEGER PRIMARY KEY," + WG_YEAR + " INT," + WG_MONTH + " INT," + WG_DAY + " INT,"
                + WG_WEIGHT + " REAL" + ")";

        String CREATE_OB_TABLE = "CREATE TABLE " + TABLE_CIRCUIT + "("
                + OB_ID + " INTEGER PRIMARY KEY," + OB_YEAR + " INT," + WG_MONTH + " INT," + WG_DAY + " INT,"
                + OB_CHEST + " REAL," + OB_WAIST + " REAL" + ")";



        db.execSQL(CREATE_INFROMATION_TABLE);
        db.execSQL(CREATE_WEIGHT_TABLE);
        db.execSQL(CREATE_OB_TABLE);



    }


    //upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INFORMATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEIGHT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CIRCUIT);

        //create tables again
        onCreate(db);
    }

// CRUD operation (Create, Read, Update, Delete)

    //adding new information
    void addInformation(information information) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(INF_NAME, information.getName());
        values.put(INF_HEIGHT, information.getHeight());
        values.put(INF_WEIGHT, information.getWeight());
        values.put(INF_TARGETWEIGHT, information.getTargetWeight());
        values.put(INF_AGE, information.getAge());
        db.insert(TABLE_INFORMATION, null, values);
        db.close();
    }

    void addWeight (weight weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WG_YEAR, weight.getYear());
        values.put(WG_MONTH, weight.getMonth());
        values.put(WG_DAY, weight.getDay());
        values.put(WG_WEIGHT, weight.getWeight());
        db.insert(TABLE_WEIGHT, null, values);
        db.close();
    }

    void addCircuit(circuit circuit){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OB_YEAR, circuit.getYear());
        values.put(OB_MONTH, circuit.getMonth());
        values.put(OB_DAY, circuit.getDay());
        values.put(OB_CHEST, circuit.getChest());
        values.put(OB_WAIST, circuit.getWaist());
        db.insert(TABLE_CIRCUIT, null, values);
        db.close();
    }




    //gettign single infomration
    information getInformation(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_INFORMATION, new String[]{INF_ID, INF_NAME,
                        INF_HEIGHT, INF_WEIGHT, INF_TARGETWEIGHT, INF_AGE},
                        INF_ID + "=?",new String[]{String.valueOf(id)},
                        null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        information information = new information(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)),
                Double.parseDouble(cursor.getString(3)), Double.parseDouble(cursor.getString(4)),
                Integer.parseInt(cursor.getString(5)));
        return information;
    }

    weight getWaight(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_WEIGHT, new String[]{WG_ID, WG_YEAR, WG_MONTH, WG_DAY, WG_WEIGHT},
                        WG_ID + "=?", new String[] {String.valueOf(id)},
                        null,null,null,null);
        if (cursor != null)
            cursor.moveToFirst();
        weight weight = new weight(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),
                Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)),
                Double.parseDouble(cursor.getString(4)));
        return weight;
    }

    circuit getCircuit(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CIRCUIT, new String[]{OB_ID, OB_YEAR, OB_MONTH, OB_DAY, OB_CHEST, OB_WAIST},
                        OB_ID + "=?", new String[]{String.valueOf(id)},
                        null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        circuit circuit = new circuit(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),
                Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)),
                Double.parseDouble(cursor.getString(4)), Double.parseDouble(cursor.getString(5)));
        return circuit;
    }




    //getting all infortmacion
    public List<information> getAllInformation() {
        List<information> informationList = new ArrayList<information>();
        String selectQuery = "SELECT * FROM " + TABLE_INFORMATION;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                information information = new information();
                information.setId(Integer.parseInt(cursor.getString(0)));
                information.setName(cursor.getString(1));
                information.setHeight(Integer.parseInt(cursor.getString(2)));
                information.setWeight(Double.parseDouble(cursor.getString(3)));
                information.setTargetWeight(Double.parseDouble(cursor.getString(4)));
                information.setAge(Integer.parseInt(cursor.getString(5)));
                informationList.add(information);
            } while (cursor.moveToNext());
        }
        return informationList;
    }

    public List<weight> getAllWeight() {
        List<weight> weightList = new ArrayList<weight>();
        String selectQuery = "SELECT * FROM " + TABLE_WEIGHT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do {
                weight weight = new weight();
                weight.setId(Integer.parseInt(cursor.getString(0)));
                weight.setYear(Integer.parseInt(cursor.getString(1)));
                weight.setMonth(Integer.parseInt(cursor.getString(2)));
                weight.setDay(Integer.parseInt(cursor.getString(3)));
                weight.setWeight(Double.parseDouble(cursor.getString(4)));
                weightList.add(weight);
            } while (cursor.moveToNext());
        }
    return weightList;
    }


    public List<circuit> getAllCircuit(){
        List<circuit> circuitList = new ArrayList<circuit>();
        String selectQuery = "SELECT * FROM " + TABLE_CIRCUIT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                circuit circuit = new circuit();
                circuit.setId(Integer.parseInt(cursor.getString(0)));
                circuit.setYear(Integer.parseInt(cursor.getString(1)));
                circuit.setMonth(Integer.parseInt(cursor.getString(2)));
                circuit.setDay(Integer.parseInt(cursor.getString(3)));
                circuit.setChest(Double.parseDouble(cursor.getString(4)));
                circuit.setWaist(Double.parseDouble(cursor.getString(5)));
                circuitList.add(circuit);
            } while (cursor.moveToNext());
        }
        return circuitList;
    }



    //updating single information
    public int updateInformation(information information) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(INF_NAME, information.getName());
        values.put(INF_HEIGHT, information.getHeight());
        values.put(INF_WEIGHT, information.getWeight());
        values.put(INF_TARGETWEIGHT, information.getTargetWeight());
        values.put(INF_AGE, information.getAge());
        //updating row
        return db.update(TABLE_INFORMATION, values, INF_ID + " = ?",
                new String[]{String.valueOf(information.getId())});
    }

    public int updateWeight(weight weight){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WG_YEAR, weight.getYear());
        values.put(WG_MONTH, weight.getMonth());
        values.put(WG_DAY, weight.getDay());
        values.put(WG_WEIGHT, weight.getWeight());

        return db.update(TABLE_WEIGHT, values, WG_ID + " = ?",
                new String[]{String.valueOf(weight.getId())});
    }


    public int updateCircuit(circuit circuit){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OB_YEAR, circuit.getYear());
        values.put(OB_MONTH, circuit.getMonth());
        values.put(OB_DAY, circuit.getDay());
        values.put(OB_CHEST, circuit.getChest());
        values.put(OB_WAIST, circuit.getWaist());
        return db.update(TABLE_CIRCUIT, values, OB_ID + " = ?",
                new String[]{String.valueOf(circuit.getId())});
    }


    //Deleting single information
    public void deleteInformation(information information) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_INFORMATION, INF_ID + " = ?",
                new String[]{String.valueOf(information.getId())});
        db.close();
    }

    public void deleteWeight(weight weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WEIGHT, WG_ID + " = ?",
                new String[] {String.valueOf(weight.getId())});
        db.close();
    }


    public void deleteCircuit(circuit circuit) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CIRCUIT, OB_ID + "= ?",
                new String[]{String.valueOf(circuit.getId())});
        db.close();
    }


}
