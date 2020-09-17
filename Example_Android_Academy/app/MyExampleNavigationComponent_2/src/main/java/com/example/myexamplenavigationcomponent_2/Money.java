package com.example.myexamplenavigationcomponent_2;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Money implements Parcelable {
    private String recipient;
    private BigDecimal amount;

    protected Money(Parcel in) {
        recipient = in.readString();
    }

    public static final Creator<Money> CREATOR = new Creator<Money>() {
        @Override
        public Money createFromParcel(Parcel in) {
            return new Money(in);
        }

        @Override
        public Money[] newArray(int size) {
            return new Money[size];
        }
    };

    public String getRecipient() {
        return recipient;
    }

    public Money(@Nullable String recipient,@Nullable BigDecimal amount) {
        this.recipient = recipient;
        this.amount = amount;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {

        this.amount = amount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    @NotNull
    @Override
    public String toString() {
        return "Money{" +
                "recipient='" + recipient + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(recipient);
    }
}
