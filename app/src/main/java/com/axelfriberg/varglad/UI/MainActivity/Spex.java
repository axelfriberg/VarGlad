package com.axelfriberg.varglad.UI.MainActivity;

class Spex {
    private final String mTitle;
    private final int mYear;
    private final Semester mSemester;
    private final int mPosterID;

    enum Semester {
        FALL, SPRING
    }

    Spex(String title, int year, Semester semester, int posterID) {
        this.mTitle = title;
        this.mYear = year;
        this.mSemester = semester;
        this.mPosterID = posterID;
    }

    public String getTitle() {
        return mTitle;
    }

    int getPosterID() {
        return mPosterID;
    }
}
