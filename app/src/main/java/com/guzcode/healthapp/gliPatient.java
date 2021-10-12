
package com.guzcode.healthapp;

        import android.os.Build;


public class gliPatient {
    int id;
    String name;
    String lName;
    String BornDate;
    String cc;
    int gliLevel;
    String state;
    String tip;
    String eps;

    public static String gliCalc(int gli) {
        if (gli > 7 && gli > 14 ) {
            return "ISOLATED HYPERGLYCAEMIA";
        }
        if (gli >= 14 &&  gli <33) {
            return "DIABETIC CETOACIDOSIS";
        }
        if (gli >= 33) {
            return "HYPEROSMOLAR HYPERGLUCEMIC NON-KETOSIC STATE";
        }

        return "healthy";

    }
    public static String tipCalc(String state) {
        if (state == "ISOLATED HYPERGLYCAEMIA" ) {
            return "Indicate fasting blood glucose and TGP in patients without diagnosis. - Yes\n" +
                    "dehydration, oral rehydration or EV as required. -\n" +
                    "Reassess therapeutic behavior in diabetics and compliance with the\n" +
                    "pillars. - Reassess doses of hypoglycemic agents.";
        }
        if (state == "DIABETIC CETOACIDOSIS") {
            return "Coordinate transfer and start treatment. - Hydration with Solution\n" +
                    "saline 40 ml / Kg in the first 4 hours. 1-2 L the first hour. -\n" +
                    "Administer potassium when diuresis or signs of\n" +
                    "hypokalemia (ST depression, U wave ≤ 1mv, U≤ T waves). - To avoid\n" +
                    "insulin until signs of hypokalaemia disappear. - Administer simple insulin 0.1 U / kg IV after hydrating";
        }
        if (state == "HYPEROSMOLAR HYPERGLUCEMIC NON-KETOSIC STATE") {
            return "Coordinate transfer and start treatment. - Hydration with Solution\n" +
                    "Saline 10-15 ml / Kg / h until hemodynamic stability is achieved. -\n" +
                    "Administer potassium when diuresis or signs of\n" +
                    "hypokalemia (ST depression, U wave ≤ 1mv, U≤ T waves).";
        }

        return "healthy";

    }
}

