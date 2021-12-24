package me.nick22985.chatAffections.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class affectionsRegister {
    @Getter
    private static final affectionsRegister instance = new affectionsRegister();

    @Getter
    private final List<Feelings> loadedFeelings = new ArrayList<>();

    public void createFeeling(final String name, final String sender, final String target, final String permmisionNpde, final Boolean isEnable) {
//        final Feelings feelings = new Feelings(name, sender, target, permmisionNpde, isEnable);
//        affectionsRegister.add(feelings);
    }
}
