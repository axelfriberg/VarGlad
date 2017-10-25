package com.axelfriberg.varglad.UI.MainActivity;

import java.util.Comparator;

class Spex {
    private final String mTitle;
    private final int mYear;
    private final Semester mSemester;
    private final int mPosterID;

    enum Semester {
        SPRING, FALL
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

    int getYear() {
        return mYear;
    }

    Semester getSemester() {
        return mSemester;
    }

    int getPosterID() {
        return mPosterID;
    }

    static class YearComparator implements Comparator<Spex> {

        @Override
        public int compare(Spex a, Spex b) {
            int yearA = a.getYear();
            int yearB = b.getYear();
            if(yearA < yearB)
                return -1;
            else if(yearA == yearB){
                return a.getSemester().compareTo(b.getSemester());
            } else {
                return 1;
            }
        }

        @Override
        public Comparator<Spex> reversed() {
            return new Comparator<Spex>() {
                @Override
                public int compare(Spex a, Spex b) {
                    int yearA = a.getYear();
                    int yearB = b.getYear();
                    if(yearA < yearB)
                        return 1;
                    else if(yearA == yearB){
                        return b.getSemester().compareTo(a.getSemester());
                    } else {
                        return -1;
                    }
                }
            };
        }
    }
}
