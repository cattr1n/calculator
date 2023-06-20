package com.cattr1ne.myapplication26;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    // задание полей
    float telescopePrice = 14000; // стоимость астрономического телескопа
    int account = 1000; // счёт пользователя
    float wage = 2500; // заработная плата в месяц
    float percentBank = 5; // годовая процентная ставка за ипотеку
    float spendablePercent = 100;
    float[] monthlyPayments = new float[60]; // создание массива

    public void calcCredit() {
        float creditBalance =  telescopePrice + calcPercentOf( telescopePrice, percentBank ) - account;
        account = 0;

        for (int i = 0 ; creditBalance > 0 && i < 60; i++) {
            account += calcPercentOf(wage,  spendablePercent);
            if (i % 12 == 0 && i != 0) creditBalance += calcPercentOf(creditBalance,  percentBank );
            monthlyPayments[i] = Math.min(creditBalance, account);
            creditBalance -= account;
            account = 0;
            System.out.println(creditBalance);
        }
    }

    public float calcPercentOf(float amount, float percent) {
        return amount * percent / 100;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView creditTermOut = findViewById(R.id.creditTermOut);
        TextView monthlyPaymentsOut = findViewById(R.id.monthlyPaymentsOut);

        StringBuilder monthlyPaymentsOutText = new StringBuilder();
        int monthCounter = 0;

        monthlyPaymentsOutText.append("Первоначальный взнос: ").append(account).append('\n');
        monthlyPaymentsOutText.append("Выплаты: ");

        calcCredit();
        for (float payment : monthlyPayments) {
            if (payment <= 0) break;
            monthlyPaymentsOutText.append(payment).append(" монет; ");
            monthCounter++;
        }

        creditTermOut.setText("Кредит будет выплачиваться " + monthCounter + " месяцев");
        monthlyPaymentsOut.setText(monthlyPaymentsOutText.toString());
    }
}




