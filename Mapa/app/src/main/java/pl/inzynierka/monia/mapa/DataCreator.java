package pl.inzynierka.monia.mapa;

import android.content.Context;

import io.realm.Realm;

public class DataCreator {
    private static final char campusA = 'a';
    private static final char campusB = 'b';
    private static final char campusC = 'c';
    private static final char campusD = 'd';
    private static final char institute = 'i';
    private static final char department = 'k';
    private Context context;
    private Realm realm;

    public DataCreator(Context context, Realm realm) {
        this.context = context;
        this.realm = realm;
    }

    public void addData() {
        final Data data = new Data(realm);

        addFaculties(data);
        addBuildings(data);
        addUnits(data);
    }

    private void addUnits(Data data) {
        addInstitutes(data);
        addDepartments(data);
    }

    private void addDepartments(Data data) {
        data.createUnit("", department, 0, "", "", "", "", 0, 0);
        // ...
    }

    private void addInstitutes(Data data) {
        data.createUnit("", institute, 0, "", "", "", "", 0, 0);
        // ...
    }

    private void addBuildings(Data data) {
        addCampusA(data);
        addCampusB(data);
        addCampusC(data);
        addCampusD(data);
    }

    private void addCampusD(Data data) {
        data.createBuilding("", campusB, 0, 0, 0);
        // ...
    }

    private void addCampusC(Data data) {
        data.createBuilding("", campusC, 0, 0, 0);
        // ...
    }

    private void addCampusB(Data data) {
        data.createBuilding("", campusD, 0, 0, 0);
        // ...
    }

    private void addCampusA(Data data) {
        data.createBuilding(context.getString(R.string.building_a2), campusA, 2, 51.754678, 19.452766);
        data.createBuilding(context.getString(R.string.building_a3), campusA, 3, 51.754739, 19.453269);
        data.createBuilding(context.getString(R.string.building_a4), campusA, 4, 51.754540, 19.454675);
        data.createBuilding(context.getString(R.string.building_a5), campusA, 5, 51.754258, 19.454889);
        data.createBuilding(context.getString(R.string.building_a6), campusA, 6, 51.754135, 19.453981);
        data.createBuilding(context.getString(R.string.building_a8), campusA, 8, 51.753540, 19.452974);
        data.createBuilding(context.getString(R.string.building_a9), campusA, 9, 51.753489, 19.453387);
        data.createBuilding(context.getString(R.string.building_a10), campusA, 10, 51.752597, 19.453177);
        data.createBuilding(context.getString(R.string.building_a11), campusA, 11, 51.753129, 19.453547);
        data.createBuilding(context.getString(R.string.building_a12), campusA, 12, 51.753058, 19.454687);
        data.createBuilding(context.getString(R.string.building_a13), campusA, 13, 51.752442, 19.453767);
        data.createBuilding(context.getString(R.string.building_a14), campusA, 14, 51.753094, 19.455118);
        data.createBuilding(context.getString(R.string.building_a16), campusA, 16, 51.755109, 19.451514);
        data.createBuilding(context.getString(R.string.building_a17), campusA, 17, 51.755100, 19.450621);
        data.createBuilding(context.getString(R.string.building_a18), campusA, 18, 51.754764, 19.451397);
        data.createBuilding(context.getString(R.string.building_a19), campusA, 19, 51.753797, 19.452048);
        data.createBuilding(context.getString(R.string.building_a20), campusA, 20, 51.753360, 19.452204);
        data.createBuilding(context.getString(R.string.building_a21), campusA, 21, 51.753025, 19.451643);
        data.createBuilding(context.getString(R.string.building_a22), campusA, 22, 51.752673, 19.452375);
        data.createBuilding(context.getString(R.string.building_a24), campusA, 24, 51.754491, 19.450863);
        data.createBuilding(context.getString(R.string.building_a26), campusA, 26, 51.754118, 19.450718);
        data.createBuilding(context.getString(R.string.building_a27), campusA, 27, 51.753904, 19.450934);
        data.createBuilding(context.getString(R.string.building_a28), campusA, 28, 51.753604, 19.450957);
        data.createBuilding(context.getString(R.string.building_a30), campusA, 30, 51.752613, 19.451285);
        data.createBuilding(context.getString(R.string.building_a31), campusA, 31, 51.753833, 19.449636);
        data.createBuilding(context.getString(R.string.building_a33), campusA, 33, 51.752786, 19.450030);
    }

    private void addFaculties(Data data) {
        data.createFaculty(
                context.getString(
                        R.string.faculty_of_mechanical_engineering),
                1, "w-1@adm.p.lodz.pl", "42 636 46 83", "42 631 22 02", "42 631 27 02");
        data.createFaculty(
                context.getString(
                        R.string.faculty_of_electrical_electronic_computer_and_control_engineering),
                2, "deanelec@adm.p.lodz.pl", "42 631 25 00", "42 631 25 02", "42 636 47 02");
        data.createFaculty(
                context.getString(
                        R.string.faculty_of_chemistry),
                3, "w-3@adm.p.lodz.pl", "42 631 31 01", "42 631 31 02", "42 631 31 03");
        data.createFaculty(
                context.getString(
                        R.string.faculty_of_material_technologies_and_textile_design),
                4, "w-4@adm.p.lodz.pl", "42 636 48 23", "42 631 33 00", "42 631 33 01");
        data.createFaculty(
                context.getString(
                        R.string.faculty_of_biotechnology_and_food_sciences),
                5, "deanbiof@adm.p.lodz.pl", "42 631 34 00", "42 631 34 01", "42 631 34 02");
        data.createFaculty(
                context.getString(
                        R.string.faculty_of_civil_engineering_architecture_and_environmental_engineering),
                6, "w-6@adm.p.lodz.pl", "42 631 35 00", "42 631 35 01", "42 631 35 02");
        data.createFaculty(
                context.getString(
                        R.string.faculty_of_technical_physics_information_technology_and_applied_mathematics),
                7, "w-7@adm.p.lodz.pl", "42 631 36 00", "42 631 36 03", "42 631 36 02");
        data.createFaculty(
                context.getString(
                        R.string.faculty_of_organization_and_management),
                9, "w-9@adm.p.lodz.pl", "42 631 37 51", "42 631 36 85", "42 684 79 93");
        data.createFaculty(
                context.getString(
                        R.string.faculty_of_process_and_environmental_engineering),
                10, "sekretariat@wipos.p.lodz.pl", "42 631 37 00", "42 631 37 01", "42 636 56 63");
    }
}
