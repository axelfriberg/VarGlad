package com.axelfriberg.varglad.ui.mainactivity;

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

    int getPosterID() {
        return mPosterID;
    }

    static class YearComparator implements Comparator<Spex> {

        @Override
        public int compare(Spex a, Spex b) {
            if (a.mYear < b.mYear)
                return -1;
            else if (a.mYear == b.mYear) {
                return a.mSemester.compareTo(b.mSemester);
            } else {
                return 1;
            }
        }

        @Override
        public Comparator<Spex> reversed() {
            return new Comparator<Spex>() {
                @Override
                public int compare(Spex s1, Spex s2) {
                    if (s1.mYear < s2.mYear)
                        return 1;
                    else if (s1.mYear == s2.mYear) {
                        return s2.mSemester.compareTo(s1.mSemester);
                    } else {
                        return -1;
                    }
                }
            };
        }
    }

    static class TitleComparator implements Comparator<Spex> {
        @Override
        public int compare(Spex s1, Spex s2) {
            return s1.mTitle.compareToIgnoreCase(s2.mTitle);
        }

        @Override
        public Comparator<Spex> reversed() {
            return new Comparator<Spex>() {
                @Override
                public int compare(Spex s1, Spex s2) {
                    return s2.mTitle.compareToIgnoreCase(s1.mTitle);
                }
            };
        }
    }
}
