package pl.inzynierka.monia.mapa;

import android.content.Context;
import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmList;
import pl.inzynierka.monia.mapa.models.BuildingID;

public class DataCreator {
    private static final String campusA = "a";
    private static final String campusB = "b";
    private static final String campusC = "c";
    private static final String campusD = "d";
    private static final String other = "e";
    private static final String institute = "i";
    private static final String department = "k";
    private int[] tempBuildingsTab;
    private RealmList<BuildingID> tempBuildingsID;
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

    private void addFaculties(Data data) {
        data.createFaculty(context.getString(R.string.faculty_w1), 1,
                "w-1@adm.p.lodz.pl", "42 636 46 83", "42 631 22 02", "42 631 27 02");
        data.createFaculty(context.getString(R.string.faculty_w2), 2,
                "deanelec@adm.p.lodz.pl", "42 631 25 00", "42 631 25 02", "42 636 47 02");
        data.createFaculty(context.getString(R.string.faculty_w3), 3,
                "w-3@adm.p.lodz.pl", "42 631 31 01", "42 631 31 02", "42 631 31 03");
        data.createFaculty(context.getString(R.string.faculty_w4), 4,
                "w-4@adm.p.lodz.pl", "42 636 48 23", "42 631 33 00", "42 631 33 01");
        data.createFaculty(context.getString(R.string.faculty_w5), 5,
                "deanbiof@adm.p.lodz.pl", "42 631 34 00", "42 631 34 01", "42 631 34 02");
        data.createFaculty(context.getString(R.string.faculty_w6), 6,
                "w-6@adm.p.lodz.pl", "42 631 35 00", "42 631 35 01", "42 631 35 02");
        data.createFaculty(context.getString(R.string.faculty_w7), 7,
                "w-7@adm.p.lodz.pl", "42 631 36 00", "42 631 36 03", "42 631 36 02");
        data.createFaculty(context.getString(R.string.faculty_w9), 9,
                "w-9@adm.p.lodz.pl", "42 631 37 51", "42 631 36 85", "42 684 79 93");
        data.createFaculty(context.getString(R.string.faculty_w10), 10,
                "sekretariat@wipos.p.lodz.pl", "42 631 37 00", "42 631 37 01", "42 636 56 63");
    }

    private void addBuildings(Data data) {
        addCampusA(data);
        addCampusB(data);
        addCampusC(data);
        addCampusD(data);
        addOther(data);
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

    private void addCampusB(Data data) {
        data.createBuilding(context.getString(R.string.building_b1), campusB, 1, 51.748690, 19.455298);
        data.createBuilding(context.getString(R.string.building_b2), campusB, 2, 51.748952, 19.454548);
        data.createBuilding(context.getString(R.string.building_b3), campusB, 3, 51.748577, 19.453160);
        data.createBuilding(context.getString(R.string.building_b4), campusB, 4, 51.748030, 19.455846);
        data.createBuilding(context.getString(R.string.building_b5), campusB, 5, 51.747973, 19.455376);
        data.createBuilding(context.getString(R.string.building_b6), campusB, 6, 51.747708, 19.453017);
        data.createBuilding(context.getString(R.string.building_b7), campusB, 7, 51.747590, 19.451438);
        data.createBuilding(context.getString(R.string.building_b9), campusB, 9, 51.747278, 19.453800);
        data.createBuilding(context.getString(R.string.building_b10), campusB, 10, 51.747304, 19.455375);
        data.createBuilding(context.getString(R.string.building_b11), campusB, 11, 51.747361, 19.456006);
        data.createBuilding(context.getString(R.string.building_b12), campusB, 12, 51.747128, 19.455393);
        data.createBuilding(context.getString(R.string.building_b13), campusB, 13, 51.746810, 19.454276);
        data.createBuilding(context.getString(R.string.building_b14), campusB, 14, 51.746507, 19.455333);
        data.createBuilding(context.getString(R.string.building_b15), campusB, 15, 51.746598, 19.455795);
        data.createBuilding(context.getString(R.string.building_b16), campusB, 16, 51.746403, 19.453270);
        data.createBuilding(context.getString(R.string.building_b17), campusB, 17, 51.746021, 19.454671);
        data.createBuilding(context.getString(R.string.building_b18), campusB, 18, 51.746043, 19.455606);
        data.createBuilding(context.getString(R.string.building_b19), campusB, 19, 51.747120, 19.455917);
        data.createBuilding(context.getString(R.string.building_b22), campusB, 22, 51.745635, 19.454747);
        data.createBuilding(context.getString(R.string.building_b24_b25), campusB, 24, 51.745435, 19.451648);
        data.createBuilding(context.getString(R.string.building_b24_b25), campusB, 25, 51.745395, 19.451339);
    }

    private void addCampusC(Data data) {
        data.createBuilding(context.getString(R.string.building_c2), campusC, 2, 51.744607, 19.448511);
        data.createBuilding(context.getString(R.string.building_c3), campusC, 3, 51.745112, 19.449972);
        data.createBuilding(context.getString(R.string.building_c4), campusC, 4, 51.745486, 19.449312);
        data.createBuilding(context.getString(R.string.building_c5), campusC, 5, 51.745644, 19.449862);
        data.createBuilding(context.getString(R.string.building_c6), campusC, 6, 51.745836, 19.449239);
        data.createBuilding(context.getString(R.string.building_c7), campusC, 7, 51.745812, 19.448823);
        data.createBuilding(context.getString(R.string.building_c9), campusC, 9, 51.746113, 19.450128);
        data.createBuilding(context.getString(R.string.building_c10), campusC, 10, 51.746336, 19.450206);
        data.createBuilding(context.getString(R.string.building_c11), campusC, 11, 51.746682, 19.450141);
        data.createBuilding(context.getString(R.string.building_c12), campusC, 12, 51.747406, 19.450000);
        data.createBuilding(context.getString(R.string.building_c13), campusC, 13, 51.748220, 19.449792);
        data.createBuilding(context.getString(R.string.building_c14), campusC, 14, 51.748662, 19.449630);
        data.createBuilding(context.getString(R.string.building_c15), campusC, 15, 51.748965, 19.449445);
        data.createBuilding(context.getString(R.string.building_c16), campusC, 16, 51.751432, 19.447811);
    }

    private void addCampusD(Data data) {
        data.createBuilding(context.getString(R.string.building_d1), campusD, 1, 51.749538, 19.460980);
    }

    private void addOther(Data data) {
        data.createBuilding(context.getString(R.string.building_e1), other, 1, 51.746912, 19.459785);
        data.createBuilding(context.getString(R.string.building_e2), other, 2, 51.778973, 19.494322);
    }


    private void addUnits(Data data) {
        addInstitutes(data);
        addDepartments(data);
        addOtherUnits(data);
    }

    private void addInstitutes(Data data) {
        addW1Institutes(data);
        addW2Institutes(data);
        addW3Institutes(data);
        addW4Institutes(data);
        addW5Institutes(data);
        addW6Institutes(data);
        addW7Institutes(data);
        addW9Institutes(data);
    }

    private void addW1Institutes(Data data) {
        tempBuildingsTab = new int[]{15, 17, 18, 19};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i7), institute, 7,
                "inzynieria.materialowa@info.p.lodz.pl", "42 631 30 30", "", "42 631 30 38", 1,
                tempBuildingsID);
        tempBuildingsTab = new int[]{15, 17, 19};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i8), institute, 8,
                "i-8@adm.p.lodz.pl", "42 636 20 91", "42 631 22 99", "42 636 57 26", 1,
                tempBuildingsID);
        tempBuildingsTab = new int[]{38, 39};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i10), institute, 10,
                "i-10@adm.p.lodz.pl", "42 631 23 64", "", "42 631 24 78", 1,
                tempBuildingsID);
    }

    @NonNull
    private RealmList<BuildingID> getBuildingIDs(int[] tempBuildingsTab) {
        RealmList<BuildingID> tempBuildingsID = new RealmList<>();
        for (int building : tempBuildingsTab) {
            tempBuildingsID.add(new BuildingID(building));
        }
        return tempBuildingsID;
    }

    private void addW2Institutes(Data data) {
        tempBuildingsTab = new int[]{8};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i12), institute, 12,
                "i-12@adm.p.lodz.pl", "42 631 25 19", "42 636 22 81", "42 636 22 81", 2,
                tempBuildingsID);
        tempBuildingsTab = new int[]{8, 10, 49};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i13), institute, 13,
                "i-13@adm.p.lodz.pl", "42 631 25 60", "42 631 25 47", "42 631 25 51", 2,
                tempBuildingsID);
        tempBuildingsTab = new int[]{10};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i14), institute, 14,
                "i-14@adm.p.lodz.pl", "42 631 25 71", "42 631 25 81", "", 2,
                tempBuildingsID);
        tempBuildingsTab = new int[]{8, 9, 49};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i15), institute, 15,
                "i-15@adm.p.lodz.pl", "42 631 25 90", "42 631 25 65", "42 631 26 06", 2,
                tempBuildingsID);
        tempBuildingsTab = new int[]{34};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i16), institute, 16,
                "i-16@adm.p.lodz.pl", "42 636 00 65", "42 631 26 26", "42 636 22 38", 2,
                tempBuildingsID);
        tempBuildingsTab = new int[]{8, 52, 53};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i24), institute, 24,
                "katedra@kis.p.lodz.pl", "42 631 27 50", "", "42 631 27 55", 2,
                tempBuildingsID);
    }

    private void addW3Institutes(Data data) {
        tempBuildingsTab = new int[]{6, 21, 22, 26};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i17), institute, 17,
                "jacek.rynkowski@p.lodz.pl", "42 631 31 17", "", "42 631 31 03", 3,
                tempBuildingsID);
        tempBuildingsTab = new int[]{22};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i18), institute, 18,
                "zbigniew.kaminski@p.lodz.pl", "42 631 31 40", "42 631 31 42", "42 636 55 30", 3,
                tempBuildingsID);
        tempBuildingsTab = new int[]{22, 48};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i19), institute, 19,
                "mitr@mitr.p.lodz.pl", "42 631 31 88", "42 631 31 05", "42 631 30 84", 3,
                tempBuildingsID);
        tempBuildingsTab = new int[]{20};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i20), institute, 20,
                "polbarw@p.lodz.pl", "42 631 32 09", "42 636 25 43", "42 636 25 43", 3,
                tempBuildingsID);
    }

    private void addW4Institutes(Data data) {
        tempBuildingsTab = new int[]{13, 26};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i41), institute, 41,
                "renata.szukalska@p.lodz.pl", "42 631 33 50", "42 631 33 43", "42 631 33 43", 4,
                tempBuildingsID);
    }

    private void addW5Institutes(Data data) {
        tempBuildingsTab = new int[]{1, 3};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i28), institute, 28,
                "i-28@adm.p.lodz.pl", "42 631 34 10", "", "42 631 28 42", 5,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.institute_i29), institute, 29,
                "biochem@info.p.lodz.pl", "42 631 34 42", "42 631 34 33", "42 636 66 18", 5,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.institute_i30), institute, 30,
                "i-30@adm.p.lodz.pl", "42 631 34 66", "42 631 32 43", "42 631 32 43", 5,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.institute_i31), institute, 31,
                "itfim@p.lodz.pl", "42 636 36 39", "", "42 636 59 76", 5,
                tempBuildingsID);
    }

    private void addW6Institutes(Data data) {
        tempBuildingsTab = new int[]{32, 41};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i35), institute, 35,
                "", "42 631 35 30", "42 631 35 45", "42 636 78 73", 6,
                tempBuildingsID);
        tempBuildingsTab = new int[]{33};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i612), institute, 612,
                "teresa.kostrz@p.lodz.pl", "42 631 35 21", "42 631 35 23", "42 631 35 16", 6,
                tempBuildingsID);
    }

    private void addW7Institutes(Data data) {
        tempBuildingsTab = new int[]{12, 34};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i1), institute, 1,
                "office@ics.p.lodz.pl ", "42 632 97 57", "", "42 630 34 14", 7,
                tempBuildingsID);
        tempBuildingsTab = new int[]{34};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i2), institute, 2,
                "biuro_im@info.p.lodz.pl ", "42 631 36 17", "42 631 38 65", "42 631 38 65", 7,
                tempBuildingsID);
        tempBuildingsTab = new int[]{39, 40};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i3), institute, 3,
                "i-3@adm.p.lodz.pl", "42 636 31 39", "", "42 631 36 39", 7,
                tempBuildingsID);
    }

    private void addW9Institutes(Data data) {
        tempBuildingsTab = new int[]{34, 62};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i910), institute, 910,
                "ryszard.gradzki@p.lodz.pl", "42 631 37 67", "42 631 36 91", "42 631 37 67", 8,
                tempBuildingsID);
    }

    private void addDepartments(Data data) {
        addW1Departments(data);
        addW2Departments(data);
        addW3Departments(data);
        addW4Departments(data);
        addW6Departments(data);
        addW9Departments(data);
        addW10Departments(data);
    }

    private void addW1Departments(Data data) {
        tempBuildingsTab = new int[]{15, 19, 25};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k111), department, 111,
                "k-111@adm.p.lodz.pl", "42 631 23 93", "", "", 1,
                tempBuildingsID);
        tempBuildingsTab = new int[]{19};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k12), department, 12,
                "k-12@adm.p.lodz.pl", "42 631 24 28", "", "", 1,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.department_k13), department, 13,
                "k-13@adm.p.lodz.pl", "42 631 22 31", "42 631 24 30", "42 631 27 02", 1,
                tempBuildingsID);
        tempBuildingsTab = new int[]{15, 17};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k14), department, 14,
                "andrzej.golabczak@p.lodz.pl", "42 631 22 95", "42 631 22 86", "42 636 79 63", 1,
                tempBuildingsID);
        tempBuildingsTab = new int[]{19};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k16), department, 16,
                "k16@info.p.lodz.pl", "42 631 22 25", "", "", 1,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.department_k17), department, 17,
                "agata.golnik@p.lodz.pl", "42 631 22 75", "", "42 636 51 05", 1,
                tempBuildingsID);
    }

    private void addW2Departments(Data data) {
        tempBuildingsTab = new int[]{43, 49};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k25), department, 25,
                "secretary@dmcs.p.lodz.pl", "42 631 27 27", "", "42 636 03 27", 2,
                tempBuildingsID);
        tempBuildingsTab = new int[]{9, 10};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k26), department, 26,
                "k-26@adm.p.lodz.pl", "42 631 26 61", "42 631 27 70", "42 631 27 71", 2,
                tempBuildingsID);
        tempBuildingsTab = new int[]{34};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k27), department, 27,
                "k-27@adm.p.lodz.pl", "42 636 79 99", "", "42 636 80 24", 2,
                tempBuildingsID);
    }

    private void addW3Departments(Data data) {
        tempBuildingsTab = new int[]{22};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k32), department, 32,
                "k-32@adm.p.lodz.pl", "42 631 32 05", "42 631 32 16", "42 631 32 18", 3,
                tempBuildingsID);
    }

    private void addW4Departments(Data data) {
        tempBuildingsTab = new int[]{26};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k41), department, 41,
                "piotr.kulpiński@p.lodz.pl", "42 631 33 59", "", "42 637 20 40", 4,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.department_k46), department, 46,
                "zbigniew.mikolajczyk@p.lodz.pl", "42-631-33-38", "", "", 4,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.department_k48), department, 48,
                "jolanta.wojtyniak@p.lodz.pl", "42 631 33 17", "", "42 631 33 18", 4,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.department_k411), department, 411,
                "edyta.kulik@p.lodz.pl", "42 631 33 83", "42 636 14 29", "", 4,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.department_k412), department, 412,
                "", "42 631 33 84", "", "42 631 33 84", 4,
                tempBuildingsID);
    }

    private void addW6Departments(Data data) {
        tempBuildingsTab = new int[]{33};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k61), department, 61,
                "kmm@kmm.p.lodz.pl", "42 631 35 51", "", "42 631 35 51", 6,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.department_k62), department, 62,
                "sekrk62@info.p.lodz.pl", "42 631 35 56", "", "42 631 35 56", 6,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.department_k63), department, 63,
                "kmk@p.lodz.pl", "42 631 35 64", "", "42 631 35 64", 6,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.department_k65), department, 65,
                "anna.galant@p.lodz.pl", "42 631 35 75", "", "", 6,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.department_k66), department, 66,
                "geotech@p.lodz.pl", "42 631 35 92", "42 631 35 90", "", 6,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.department_k67), department, 67,
                "wieslaw.pawlowski@p.lodz.pl", "42 631 35 16", "42 631 35 08", "42 631 35 16", 6,
                tempBuildingsID);
    }

    private void addW9Departments(Data data) {
        tempBuildingsTab = new int[]{62};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k91), department, 91,
                "k91@info.p.lodz.pl", "42 631 37 62", "", "42 636 28 24", 8,
                tempBuildingsID);
        tempBuildingsTab = new int[]{34};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k94), department, 94,
                "kzp@info.p.lodz.pl", "42 631 37 54", "42 631 32 54", "42 631 32 54", 8,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.department_k95), department, 95,
                "kieimm@info.p.lodz.pl", "42 631 36 81", "", "42 631 28 62", 8,
                tempBuildingsID);
        tempBuildingsTab = new int[]{34, 62};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k99), department, 99,
                "ksz@oizet.p.lodz.pl", "42 631 37 66", "", "42 631 37 66", 8,
                tempBuildingsID);
    }

    private void addW10Departments(Data data) {
        tempBuildingsTab = new int[]{5, 6};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k101), department, 101,
                "czeslaw.kuncewicz@p.lodz.pl", "42 631 37 27", "", "", 9,
                tempBuildingsID);
        tempBuildingsTab = new int[]{5};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k102), department, 102,
                "marek.dziubinski@p.lodz.pl", "42 631 37 34", "", "", 9,
                tempBuildingsID);
        tempBuildingsTab = new int[]{5, 31};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k103), department, 103,
                "stanleda@p.lodz.pl", "42 631 37 15", "", "", 9,
                tempBuildingsID);
        tempBuildingsTab = new int[]{5, 35};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k106), department, 106,
                "wladyslaw.kaminski@p.lodz.pl", "42 631 37 08", "", "", 9,
                tempBuildingsID);
        tempBuildingsTab = new int[]{5, 30};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k107), department, 107,
                "marek.stelmachowski@p.lodz.pl", "42 631 37 21", "", "", 9,
                tempBuildingsID);
        tempBuildingsTab = new int[]{5, 35};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k108), department, 108,
                "grzegorz.wielgosinski@p.lodz.pl", "42 631 37 95", "", "", 9,
                tempBuildingsID);
        tempBuildingsTab = new int[]{5};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k109), department, 109,
                "jacek.tyczkowski@p.lodz.pl", "42 631 37 23", "", "", 9,
                tempBuildingsID);
    }

    private void addOtherUnits(Data data) {
        tempBuildingsTab = new int[]{13};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.building_a16), other, 0,
                "ife@adm.p.lodz.pl", "42 638 38 00", "", "42 636 06 52", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{42, 43};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.building_b17), institute, 4,
                "i-4@adm.p.lodz.pl", "42 631 38 03", "42 631 38 08", "42 631 38 01", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{46, 47};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.building_b24_b25), other, 0,
                "sjo@adm.p.lodz.pl", "42 631 28 10", "42 636 32 06", "42 636 28 27", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{50};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.building_c4), other, 0,
                "wf@adm.p.lodz.pl", "42 636 32 30", "42 631 28 21", "42 636 81 91", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{49};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_1), other, 0,
                "cmf@adm.p.lodz.pl", "42 631 36 14", "42 631 36 19", "42 631 36 19", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{45};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.building_b22), other, 0,
                "sekret@lib.p.lodz.pl", "42 631 20 59", "", "42 631 29 39", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{4, 8};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_2), other, 0,
                "ck@p.lodz.pl", "42 636 22 08", "42 631 28 35", "42 631 28 39", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{44};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.building_b19), other, 0,
                "cti@adm.p.lodz.pl", "42 631 27 17", "", "42 636 64 61", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{26};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_3), other, 0,
                "towaroznawstwo@info.p.lodz.pl", "42 631 33 17", "", "", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{33};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_4), other, 0,
                "kol.gosp.przestrz@adm.p.lodz.pl", "42 631 35 03", "", "", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{34};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_5), other, 0,
                "kol.logistyki@adm.p.lodz.pl", "42 631 36 05", "", "", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{0};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_6), other, 0,
                "sieradz@p.lodz.pl", "608 652 658", "43 822 36 32", "", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{37};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_7), other, 0,
                "jjoachimiak@cdtl.pl", "42 681 61 38", "42 631 36 70", "42 681 61 39", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{0};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_8), other, 0,
                "utwpl@p.lodz.pl", "42 631 20 30", "", "", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{45};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_9), other, 0,
                "lud@p.lodz.pl", "42 631 28 09", "", "", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{40};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_10), other, 0,
                "agnieszka.roganowicz@p.lodz.pl", "42 631 28 80", "", "42 636 50 14", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{3, 55};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_11), other, 0,
                "", "", "", "", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{11};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_12), other, 0,
                "", "42 631 20 91", "", "", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{14};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_13), other, 0,
                "jadwiga.machnicka@p.lodz.pl", "42 636 89 34", "42 631 20 17", "42 636 50 43", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_14), other, 0,
                "agnieszka.kobalczyk@p.lodz.pl", "42 636 89 34", "42 631 20 18", "42 636 50 43", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{14, 27};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_15), other, 0,
                "vrector.development@adm.p.lodz.pl", "42 631 20 06", "42 631 20 05", "42 636 53 79", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{16};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_16), other, 0,
                "", "", "", "", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{23};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_17), other, 0,
                "", "42 636 62 29", "42 631 21 15", "42 636 56 15", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_18), other, 0,
                "azp@adm.p.lodz.pl", "42 631 21 04", "42 631 21 05", "", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_19), other, 0,
                "", "42 636 66 83", "42 631 21 06", "42 636 56 15", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_20), other, 0,
                "chancellor@adm.p.lodz.pl", "42 636 82 99", "42 631 20 12", "42 631 20 28", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_21), other, 0,
                "teresa.janasik@p.lodz.pl", "42 636 82 99", "42 631 20 12", "", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{24};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_22), other, 0,
                "counselingtul@gmail.com", "42 636 23 41", "", "", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_23), other, 0,
                "counselingtul@gmail.com", "42 636 23 41", "", "", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{27};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.building_b1), other, 0,
                "grazyna.migas@adm.p.lodz.pl", "42 631 20 02", "", "42 636 85 22", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_25), other, 0,
                "maria.pietrzak-spyra@p.lodz.pl", "42 631 20 40", "42 631 20 52", "", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_26), other, 0,
                "rrz@adm.p.lodz.pl", "42 631 20 83", "", "", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_27), other, 0,
                "radcy@adm.p.lodz.pl", "42 631 20 84", "", "42 636 14 81", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_28), other, 0,
                "vrector.education@adm.p.lodz.pl", "42 631 20 90", "42 631 21 33", "42 636 60 34", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_29), other, 0,
                "vrector.innovations@adm.p.lodz.pl", "42 631 20 08", "42 631 20 07", "42 636 92 35", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_30), other, 0,
                "vrector.science@adm.p.lodz.pl", "42 631 20 04", "", "42 636 29 33", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_32), other, 0,
                "rector@adm.p.lodz.pl", "42 631 20 01", "42 631 20 02", "42 636 85 22", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{29};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_33), other, 0,
                "anna.boczkowska@p.lodz.pl", "42 631 20 10", "", "", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_34), other, 0,
                "joanna.stawicka@p.lodz.pl", "42 631 20 81", "", "", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{30};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_35), other, 0,
                "sylwia.majchrzak@p.lodz.pl", "42 631 36 83", "", "", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{32};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_36), other, 0,
                "", "42 631 20 86", "", "", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_37), other, 0,
                "liceum@info.p.lodz.pl", "42 631 20 86", "", "", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{33};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_35), other, 0,
                "", "42 631 35 02", "42 631 35 03", "42 631 35 02", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{36};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_35), other, 0,
                "anna.bejmert@p.lodz.pl", "42 631 36 00", "", "", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{45};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_40), other, 0,
                "bok@edu.p.lodz.pl", "42 631 28 06", "", "", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_41), other, 0,
                "", "", "", "", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_42), other, 0,
                "", "", "", "", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_43), other, 0,
                "zamowienia@info.p.lodz.pl", "42 631 20 87", "42 631 29 52", "42 631 25 38", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_44), other, 0,
                "lud@p.lodz.pl", "42 631 28 09", "", "", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{57};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_45), other, 0,
                "radio@zak.lodz.pl", "42 631 28 44", "42 637 78 88", "", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{58};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_46), other, 0,
                "", "42 636 84 62", "", "", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_47), other, 0,
                "uslugi.pocztowe@poczta-polska.pl", "42 636 80 23", "", "", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{60};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_48), other, 0,
                "biuro@biurokarier.p.lodz.pl", "42 631 27 39", "42 631 20 98", "42 631 20 98", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_49), other, 0,
                "janina.mrozowska@p.lodz.pl", "42 631 20 19", "42 631 20 27", "42 631 29 45", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_50), other, 0,
                "sylwia.saganska@p.lodz.pl", "42 631 20 19", "", "42 631 29 45", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_51), other, 0,
                "komisje.stale@samorzad.p.lodz.pl", "42 631 28 41", "", "", 0,
                tempBuildingsID);
        data.createUnit(context.getString(R.string.other_52), other, 0,
                "", "", "", "", 0,
                tempBuildingsID);
        tempBuildingsTab = new int[]{62};
        tempBuildingsID = getBuildingIDs(tempBuildingsTab);
        data.createUnit(context.getString(R.string.other_35), other, 0,
                "w-9@adm.p.lodz.pl", "42 631 37 51", "", "", 0,
                tempBuildingsID);
    }
}
