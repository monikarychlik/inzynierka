package pl.inzynierka.monia.mapa;

import android.content.Context;

import io.realm.Realm;

public class DataCreator {
    private static final String campusA = "a";
    private static final String campusB = "b";
    private static final String campusC = "c";
    private static final String campusD = "d";
    private static final String other = "e";
    private static final String institute = "i";
    private static final String department = "k";
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
        data.createBuilding(context.getString(R.string.building_b24), campusB, 24, 51.745435, 19.451648);
        data.createBuilding(context.getString(R.string.building_b25), campusB, 25, 51.745395, 19.451339);
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
    }

    private void addInstitutes(Data data) {
        addW1Institutes(data);
        addW2Institutes(data);
        addW3Institutes(data);
        addW4Institutes(data);
        addW5Institutes(data);

    }

    private void addW1Institutes(Data data) {
        int[] tempBuildingsTab = new int[]{15, 17, 18, 19};
        data.createUnit(context.getString(R.string.institute_i7), institute, 7,
                "inzynieria.materialowa@info.p.lodz.pl", "42 631 30 30", "", "42 631 30 38", 1,
                tempBuildingsTab);
        tempBuildingsTab = new int[]{15, 17, 19};
        data.createUnit(context.getString(R.string.institute_i8), institute, 8,
                "i-8@adm.p.lodz.pl", "42 636 20 91", "42 631 22 99", "42 636 57 26", 1,
                tempBuildingsTab);
        tempBuildingsTab = new int[]{38, 39};
        data.createUnit(context.getString(R.string.institute_i10), institute, 10,
                "i-10@adm.p.lodz.pl", "42 631 23 64", "", "42 631 24 78", 1,
                tempBuildingsTab);
    }

    private void addW2Institutes(Data data) {
        int[] tempBuildingsTab = new int[]{8};
        data.createUnit(context.getString(R.string.institute_i12), institute, 12,
                "i-12@adm.p.lodz.pl", "42 631 25 19", "42 636 22 81", "42 636 22 81", 2,
                tempBuildingsTab);
        tempBuildingsTab = new int[]{8, 10, 49};
        data.createUnit(context.getString(R.string.institute_i13), institute, 13,
                "i-13@adm.p.lodz.pl", "42 631 25 60", "42 631 25 47", "42 631 25 51", 2,
                tempBuildingsTab);
        tempBuildingsTab = new int[]{10};
        data.createUnit(context.getString(R.string.institute_i14), institute, 14,
                "i-14@adm.p.lodz.pl", "42 631 25 71", "42 631 25 81", "", 2,
                tempBuildingsTab);
        tempBuildingsTab = new int[]{8, 9, 49};
        data.createUnit(context.getString(R.string.institute_i15), institute, 15,
                "i-15@adm.p.lodz.pl", "42 631 25 90", "42 631 25 65", "42 631 26 06", 2,
                tempBuildingsTab);
        tempBuildingsTab = new int[]{34};
        data.createUnit(context.getString(R.string.institute_i16), institute, 16,
                "i-16@adm.p.lodz.pl", "42 636 00 65", "42 631 26 26", "42 636 22 38", 2,
                tempBuildingsTab);
        tempBuildingsTab = new int[]{8, 52, 53};
        data.createUnit(context.getString(R.string.institute_i24), institute, 24,
                "katedra@kis.p.lodz.pl", "42 631 27 50", "", "42 631 27 55", 2,
                tempBuildingsTab);
    }

    private void addW3Institutes(Data data) {
        int[] tempBuildingsTab = new int[]{6, 21, 22, 26};
        data.createUnit(context.getString(R.string.institute_i17), institute, 17,
                "jacek.rynkowski@p.lodz.pl", "42 631 31 17", "", "42 631 31 03", 3,
                tempBuildingsTab);
        tempBuildingsTab = new int[]{22};
        data.createUnit(context.getString(R.string.institute_i18), institute, 18,
                "zbigniew.kaminski@p.lodz.pl", "42 631 31 40", "42 631 31 42", "42 636 55 30", 3,
                tempBuildingsTab);
        tempBuildingsTab = new int[]{22, 48};
        data.createUnit(context.getString(R.string.institute_i19), institute, 19,
                "mitr@mitr.p.lodz.pl", "42 631 31 88", "42 631 31 05", "42 631 30 84", 3,
                tempBuildingsTab);
        tempBuildingsTab = new int[]{20};
        data.createUnit(context.getString(R.string.institute_i20), institute, 20,
                "polbarw@p.lodz.pl", "42 631 32 09", "42 636 25 43", "42 636 25 43", 3,
                tempBuildingsTab);
    }

    private void addW4Institutes(Data data) {
        int[] tempBuildingsTab = new int[]{13, 26};
        data.createUnit(context.getString(R.string.institute_i0), institute, 0,
                "renata.szukalska@p.lodz.pl", "42 631 33 50", "42 631 33 43", "42 631 33 43", 4,
                tempBuildingsTab);
        // nie moge znalezc numeracji tego instytutu :/ wiec zostaje 0
        // obstawiam jakies 21 (21, 22, 23, 25, 26, 27?)
    }

    private void addW5Institutes(Data data) {
        int[] tempBuildingsTab = new int[]{1, 3};
        data.createUnit(context.getString(R.string.institute_i28), institute, 28,
                "i-28@adm.p.lodz.pl", "42 631 34 10", "", "42 631 28 42", 5,
                tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i29), institute, 29,
                "biochem@info.p.lodz.pl", "42 631 34 42", "42 631 34 33", "42 636 66 18", 5,
                tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i30), institute, 30,
                "i-30@adm.p.lodz.pl", "42 631 34 66", "42 631 32 43", "42 631 32 43", 5,
                tempBuildingsTab);
        data.createUnit(context.getString(R.string.institute_i31), institute, 31,
                "itfim@p.lodz.pl", "42 636 36 39", "", "42 636 59 76", 5,
                tempBuildingsTab);
    }

    private void addDepartments(Data data) {
        addW1Departments(data);
        addW2Departments(data);
        addW3Departments(data);
        addW4Departments(data);
    }

    private void addW1Departments(Data data) {
        int[] tempBuildingsTab = new int[]{15, 19, 25};
        data.createUnit(context.getString(R.string.department_k111), department, 111,
                "k-111@adm.p.lodz.pl", "42 631 23 93", "", "", 1,
                tempBuildingsTab);
        tempBuildingsTab = new int[]{19};
        data.createUnit(context.getString(R.string.department_k12), department, 12,
                "k-12@adm.p.lodz.pl", "42 631 24 28", "", "", 1,
                tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k13), department, 13,
                "k-13@adm.p.lodz.pl", "42 631 22 31", "42 631 24 30", "42 631 27 02", 1,
                tempBuildingsTab);
        tempBuildingsTab = new int[]{15, 17};
        data.createUnit(context.getString(R.string.department_k14), department, 14,
                "andrzej.golabczak@p.lodz.pl", "42 631 22 95", "42 631 22 86", "42 636 79 63", 1,
                tempBuildingsTab);
        tempBuildingsTab = new int[]{19};
        data.createUnit(context.getString(R.string.department_k16), department, 16,
                "k16@info.p.lodz.pl", "42 631 22 25", "", "", 1,
                tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k17), department, 17,
                "agata.golnik@p.lodz.pl", "42 631 22 75", "", "42 636 51 05", 1,
                tempBuildingsTab);
    }

    private void addW2Departments(Data data) {
        int[] tempBuildingsTab = new int[]{43, 49};
        data.createUnit(context.getString(R.string.department_k25), department, 25,
                "secretary@dmcs.p.lodz.pl", "42 631 27 27", "", "42 636 03 27", 2,
                tempBuildingsTab);
        tempBuildingsTab = new int[]{9, 10};
        data.createUnit(context.getString(R.string.department_k26), department, 26,
                "k-26@adm.p.lodz.pl", "42 631 26 61", "42 631 27 70", "42 631 27 71", 2,
                tempBuildingsTab);
        tempBuildingsTab = new int[]{34};
        data.createUnit(context.getString(R.string.department_k27), department, 27,
                "k-27@adm.p.lodz.pl", "42 636 79 99", "", "42 636 80 24", 2,
                tempBuildingsTab);
    }

    private void addW3Departments(Data data) {
        int[] tempBuildingsTab = new int[]{22};
        data.createUnit(context.getString(R.string.department_k32), department, 32,
                "k-32@adm.p.lodz.pl", "42 631 32 05", "42 631 32 16", "42 631 32 18", 3,
                tempBuildingsTab);
    }

    private void addW4Departments(Data data) {
        int[] tempBuildingsTab = new int[]{26};
        data.createUnit(context.getString(R.string.department_k41), department, 41,
                "piotr.kulpiński@p.lodz.pl", "42 631 33 59", "", "42 637 20 40", 4,
                tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k46), department, 46,
                "zbigniew.mikolajczyk@p.lodz.pl", "42-631-33-38", "", "", 4,
                tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k48), department, 48,
                "jolanta.wojtyniak@p.lodz.pl", "42 631 33 17", "", "42 631 33 18", 4,
                tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k411), department, 411,
                "edyta.kulik@p.lodz.pl", "42 631 33 83", "42 636 14 29", "", 4,
                tempBuildingsTab);
        data.createUnit(context.getString(R.string.department_k412), department, 412,
                "", "42 631 33 84", "", "42 631 33 84", 4,
                tempBuildingsTab);
    }
}
