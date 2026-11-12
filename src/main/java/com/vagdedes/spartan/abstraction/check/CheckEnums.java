package com.vagdedes.spartan.abstraction.check;

public class CheckEnums {

    public enum CheckType {
        NAME(
                null // class
        );

        private Check check;
        public final Class<?> executor;

        CheckType(Class<?> executor) {
            this.executor = executor;
            this.check = new Check(this);
        }

        public void resetCheck() {
            if (this.check != null) {
                this.check = new Check(this);
            }
        }

        @Override
        public String toString() {
            return super.toString().toLowerCase().replace("_", "-");
        }
    }

}
