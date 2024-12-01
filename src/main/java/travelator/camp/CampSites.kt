package travelator.camp;

import static java.util.stream.Collectors.toUnmodifiableSet;

import java.util.Set;

public class CampSites {
    public static Set<CampSite> sitesInRegion(
        Set<CampSite> sites,
        String countryISO,
        String region
    ) {
        return sites.stream()
            .filter( campSite ->
                campSite.getCountryCode().equals(countryISO) &&
                    campSite.getRegion().equalsIgnoreCase(region)
            )
            .collect(toUnmodifiableSet());
    }
}
