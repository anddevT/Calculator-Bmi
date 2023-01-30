package com.anddev404.calculatorbmi

import android.util.Log
import androidx.lifecycle.*
import com.anddev404.calculatorbmi.data.model.HeightUnit
import com.anddev404.calculatorbmi.data.model.WeightUnit
import com.anddev404.calculatorbmi.tools.CalculatorTools
import com.anddev404.calculatorbmi.tools.UnitConverter

class SharedViewModel : ViewModel() {

    ///problem jerst z usuwaniem zmiennych z livedata np bym obliczyl idealna wage na podstawie wzrostu i potem skasowal wzrost to ta waga powinna seizmienic na 0 lub wykasowac z livedata ale wtedy jak na 0 mam to musze sprawdzac czy jest 0 i na tej podstawei cos wyswietlic lub nic nie wyswietlac a gdy nic nie wstawei(chybaby trzba bylo null wstawic) to musze sprawdzac czy nula nie ma a jak mam typy bez nula , a nie da sei chyba tak ze nic nie wstawiam tak jak na poczatku podczs uruchomienia jest

//dac obserwarotr zebvy zmienialo przyciski napisy po obruceniu ekranu
//mozna by dac w idealweight, coorect weight under/overweight od razu gotowe mapu zeby zwracalo i w tych mapach dopiero obliczenia i ladniej to by wygladalo
    //dodac jeszcze wstawianie do fragmentu po zmainie trszn dac Initializer do Fragmentu

    //TOPDO mozna to uproscic i dac np minweight max weight i correct weight w osobnych live template i wtedy by sie uproscilo bo nime trzba bylo by tak sprawdzac w underOverWeight i corectweight tak tego tylko jak by sie pojawil oto by sie jednostki zmienilo i przkonwertowalo a sprawdzanie by bylow podcas przypisywania tych max i min weight i dotego rza obliczal bym a wyswietlal bym dwa wyniiki a tak trzeba dwa razy wobliczac osobno dal underoverweight oraz corectweight

    private val _weightUnit: MutableLiveData<WeightUnit> = MutableLiveData(WeightUnit.KG)

    val weightFragment = Transformations.map(_weightUnit) {
        Pair(it, _weight.value ?: 0f)

    }


    fun changeWeightUnit(unit: WeightUnit) {
        _weightUnit.value = unit
    }

    private val _heightUnit: MutableLiveData<HeightUnit> = MutableLiveData(HeightUnit.CM)
    val heightFragment = Transformations.map(_heightUnit) {
        Pair(it, _height.value ?: 0f)
    }
    val textOfHeightUnitButton: LiveData<HeightUnit> = _heightUnit
    val textOfWeightUnitButton: LiveData<WeightUnit> = _weightUnit

    fun changeHeightUnit(unit: HeightUnit) {
        _heightUnit.value = unit
    }

    private val _height: MutableLiveData<Float> = MutableLiveData()
    private val _weight: MutableLiveData<Float> = MutableLiveData()

    val height: LiveData<Float> = _height
    val weight: LiveData<Float> = _weight

    val bmi: LiveData<Float> = MediatorLiveData<Float>().apply {

        addSource(_height) {
            if (_weight.value != null) value = CalculatorTools.calculateBmi(it, _weight.value!!)
        }
        addSource(_weight) {
            if (_height.value != null) value = CalculatorTools.calculateBmi(_height.value!!, it)
        }
    }

    val idealWeight = MediatorLiveData<String>().apply {

        addSource(_weightUnit) { weightUnit ->

            val height = _height.value

            height?.let {
                value =
                    if (it > 0) {
                        UnitConverter.convertWeight(
                            CalculatorTools.calculateIdealWeight(it), weightUnit
                        )

                    } else {
                        "..."//TODO resource three dots
                    }
            }
        }

        //TODO takie cos mediator wywoluje odzielnie funkcje adsource i w nich trzeba sprawdzac czy inny livedata ejst i n tej podstawie obliczac a nie dalo by sie takiego czegos ze by sprawdzalo oba warunki i dopiero wtedy wywolyewalo raz jedny addsource bez sprawdzania innych livedata czy sa
        //TODO w underweigth/overweight trzy kropki sa nie rowno mazna je wysrrodkwoac wzgledem napsiow bo wzgledem tych dwoch pierwzych jest nierowno
        addSource(_height) { height ->

            val unitWeight = _weightUnit.value

            height?.let {
                value =
                    if (it > 0) {//tutaj sprawdzam czy wieksze ale pmoglnym wstawic w obserwatorze bo tam mam dostep do resources, algorytm zawsze zwraca zaero lub powyzej

                        UnitConverter.convertWeight(
                            CalculatorTools.calculateIdealWeight(height), unitWeight!!
                        )

                    } else {
                        "..."//TODO resource three dots
                    }

            }
        }

    }

    val correctWeight = MediatorLiveData<String>().apply {

        addSource(_weightUnit) { weightUnit ->

            val height = _height.value

            height?.let {
                value =
                    if (it > 0) {
                        "${
                            UnitConverter.convertWeight(
                                CalculatorTools.calculateMinWeight(it),
                                weightUnit
                            )
                        } - ${
                            UnitConverter.convertWeight(
                                CalculatorTools.calculateMinWeight(it),
                                weightUnit
                            )
                        }"

                    } else {
                        "..."//TODO resource three dots
                    }
            }
        }

        addSource(_height) { height ->

            val unitWeight = _weightUnit.value

            height?.let {
                value =
                    if (it > 0) {//tutaj sprawdzam czy wieksze ale pmoglnym wstawic w obserwatorze bo tam mam dostep do resources, algorytm zawsze zwraca zaero lub powyzej

                        "${
                            UnitConverter.convertWeight(
                                CalculatorTools.calculateMinWeight(height),
                                unitWeight!!
                            )
                        } - ${
                            UnitConverter.convertWeight(
                                CalculatorTools.calculateMaxWeight(height),
                                unitWeight!!
                            )
                        }"


//                        UnitConverter.convertWeight(
//                            CalculatorTools.calculateIdealWeight(height), unitWeight!!
//                        )

                    } else {
                        "..."//TODO resource three dots
                    }
//zaokraglania dac wszedzie
                //TODO jescze napsiu underoverweight nie zmeinia na samo ove lub samo underweight
            }
        }

    }


    //wg wzrostu oblioczam wage minimalna i maksymalna, porownuje aktuallna wage i w zaleznosci czy waga jest mniejsza wieksza czy miesici sie w przedziale wqypisuje
    val underOverWeight: LiveData<String> = MediatorLiveData<String>().apply {
//TODO dac tu zmiane jednosti addSource(_weightUnit) bo gdy zmieniam jednostke to powinienem zmienic takze dolny napis
        addSource(_height) { height ->

            if (height > 0) {//poprawic jak w ponizszym na   value = if (...

                val weight = _weight.value
                weight?.let {

                    value = UnitConverter.convertWeight(
                        CalculatorTools.calculateUndeOverWeight(
                            height,
                            it
                        ), _weightUnit.value!!
                    )


                }
            } else {
                value = "..."//TODO resource three dots
            }

        }

        addSource(_weight) {
            val weight = _weight.value!!
            val height = _height.value

            height?.let {
                value = if (height > 0) {

                    UnitConverter.convertWeight(
                        CalculatorTools.calculateUndeOverWeight(
                            it,
                            weight
                        ), _weightUnit.value!!
                    )

                } else {
                    "..."//TODO resource three dots
                }
            }

        }
        addSource(_weightUnit) {
            val weight = _weight.value
            val height = _height.value

            height?.let { height ->
                weight?.let { weight ->
                    value = if (height > 0) {

                        UnitConverter.convertWeight(
                            CalculatorTools.calculateUndeOverWeight(
                                height,
                                weight
                            ), _weightUnit.value!!
                        )

                    } else {
                        "..."//TODO resource three dots
                    }
                }
            }
        }
    }

    fun changeWeight(weight: Float) {
        if (_weight.value != weight) {
            _weight.value = weight
        }
    }

    fun changeHeight(height: Float) {
        if (_height.value != height) {
            Log.d("MARCIN", "VIEWMODEL CM ${height.toString()}");
            _height.value = height
        }
    }

//region middle


//endregion

//region bottom cardview


//endregion

}