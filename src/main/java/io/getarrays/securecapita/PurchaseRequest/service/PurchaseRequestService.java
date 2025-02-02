package io.getarrays.securecapita.PurchaseRequest.service;

import io.getarrays.securecapita.PurchaseRequest.dto.PurchaseRequestApprovalDto;
import io.getarrays.securecapita.PurchaseRequest.dto.PurchaseRequestDto;
import io.getarrays.securecapita.PurchaseRequest.dto.PurchaseRequestItemDto;
import io.getarrays.securecapita.PurchaseRequest.entity.PurchaseRequestApprovalHistory;
import io.getarrays.securecapita.PurchaseRequest.entity.PurchaseRequestEntity;
import io.getarrays.securecapita.PurchaseRequest.entity.PurchaseRequestItemEntity;
import io.getarrays.securecapita.PurchaseRequest.enums.PurchaseRequestStatusEnum;
import io.getarrays.securecapita.PurchaseRequest.repository.PurchaseRequestProductRepo;
import io.getarrays.securecapita.PurchaseRequest.repository.PurchaseRequestRepo;
import io.getarrays.securecapita.asserts.service.StationService;
import io.getarrays.securecapita.domain.PageResponseDto;
import io.getarrays.securecapita.domain.User;
import io.getarrays.securecapita.dto.UserDTO;
import io.getarrays.securecapita.dtomapper.UserDTOMapper;
import io.getarrays.securecapita.exception.BadRequestException;
import io.getarrays.securecapita.exception.NotAuthorizedException;
import io.getarrays.securecapita.exception.ResourceNotFoundException;
import io.getarrays.securecapita.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PurchaseRequestService implements PurchaseRequestServiceInterface {
    private final StationService stationService;

    private final PurchaseRequestProductRepo purchaseRequestProductRepo;

    private final PurchaseRequestRepo purchaseRequestRepo;

    private final UserRepository<User> userRepository;

    @Override
    public PurchaseRequestDto getPurchaseRequestById(Long purchaseRequestId) {
        PurchaseRequestEntity purchaseRequest =
                purchaseRequestRepo.findById(purchaseRequestId).orElseThrow(() -> new ResourceNotFoundException(
                        "Purchase Request not found with id " + purchaseRequestId));
        return entityToDto(purchaseRequest);
    }

    @Override
    public PageResponseDto<PurchaseRequestDto> getAllPurchaseRequests(PageRequest pageRequest) {
        Page<PurchaseRequestEntity> page = purchaseRequestRepo.findAll(pageRequest);
        return new PageResponseDto<PurchaseRequestDto>(page.getContent().stream().map(e -> entityToDto(e)).toList(),
                page);
    }

    @Override
    public void deletePurchaseRequest(Long purchaseRequestId) {
        getPurchaseRequestById(purchaseRequestId);
        purchaseRequestRepo.deleteById(purchaseRequestId);
    }

    @Override
    public void addProductsToPurchaseRequest(Long id, PurchaseRequestItemEntity purchaseRequestProduct) {

        Optional<PurchaseRequestEntity> purchaseRequestOptional = purchaseRequestRepo.findById(id);

        if (purchaseRequestOptional.isPresent()) {
            PurchaseRequestEntity purchaseRequest = purchaseRequestOptional.get();
            purchaseRequestProduct.setPurchaseRequest(purchaseRequest);
            purchaseRequestProductRepo.save(purchaseRequestProduct);
        }

    }

    @Override
    public void addPurchaseRequestProducttoPurchaseRequest(Long id, PurchaseRequestItemEntity purchaseRequestProduct) {

    }

    @Override
    public Optional<PurchaseRequestEntity> findById(Long id) {
        return purchaseRequestRepo.findById(id);
    }

    @Override
    public PurchaseRequestDto createPurchaseRequest(UserDTO userDTO, PurchaseRequestDto purchaseRequestDto) {

        PurchaseRequestEntity purchaseRequest = new PurchaseRequestEntity();
        purchaseRequest.setDate(purchaseRequestDto.getDate());
        purchaseRequest.setStation(stationService.findStationByIdOrThrow(purchaseRequestDto.getStationId()));
        purchaseRequest.setReason(purchaseRequestDto.getReason());

        List<PurchaseRequestItemEntity> requestItems = purchaseRequestDto.getRequestItems().stream().map(itemDto -> {
            PurchaseRequestItemEntity item = new PurchaseRequestItemEntity();
            item.setDate(itemDto.getDate());
            item.setRef(itemDto.getRef());
            item.setItemNumber(itemDto.getNumber());
            item.setItemDescription(itemDto.getDescription());
            item.setUnitPrice(itemDto.getUnitPrice());
            item.setEstimatedValue(itemDto.getEstimatedValue());
            item.setQuantity(itemDto.getQuantity());
            item.setPurchaseRequest(purchaseRequest);
            return item;
        }).collect(Collectors.toList());
        purchaseRequest.setStatus(PurchaseRequestStatusEnum.INITIATED);

        purchaseRequest.setRequestItems(requestItems);
        addApprovalHistory(userDTO, purchaseRequest, purchaseRequestDto.getSignature(),
                PurchaseRequestStatusEnum.INITIATED);
        return entityToDto(purchaseRequestRepo.save(purchaseRequest));
    }

    private void addApprovalHistory(UserDTO userDto, PurchaseRequestEntity purchaseRequest, String signature,
                                    PurchaseRequestStatusEnum status) {
        List<PurchaseRequestApprovalHistory> approvalHistoryList = purchaseRequest.getApprovalHistories();
        if (approvalHistoryList == null) {
            approvalHistoryList = new ArrayList<>();
        }
        PurchaseRequestApprovalHistory approval = new PurchaseRequestApprovalHistory();
        approval.setDate(new Date());
        approval.setPurchaseRequest(purchaseRequest);
        approval.setStatus(status);
        approval.setUser(userRepository.get(userDto.getId()));
        approval.setSignature(signature);
        approvalHistoryList.add(approval);
        purchaseRequest.setApprovalHistories(approvalHistoryList);
    }

//    public boolean updateStatus(Long id) {
//        Optional<PurchaseRequest> purchaseRequest = findById(id);
//        purchaseRequest.setStatus("Completed");
//
//        return saveOrUpdatepurchaseRequest(purchaseRequest);
//    }

//    @Override
//    public void addPurchaseRequestProductToPurchaseRequest(Long id, PurchaseRequest purchaseRequest) {
//      //  invoice.setInvoiceNumber(randomAlphanumeric(8).toUpperCase());
//       PurchaseRequest purchaseRequest =  purchaseRequestRepo.findBypurchaseRequestNumber(purchaseRequest)  ;
// findById(id).get();
//
//
//
//        invoice.setCustomer(customer);
//        invoiceRepository.save(invoice);
//
//   }
//how are we adding products now?
//    @Override
//    public void addPurchaseRequestProductToPurchaseRequest(Long id, PurchaseRequest purchaseRequest, int sequence) {
//        String sequenceNumber = "PR" + String.format("%06d", sequence);
//        purchaseRequest.setPurchaseRequestNumber(sequenceNumber);
//
////        PurchaseRequest purchaseRequest2 = purchaseRequestRepo.findBypurchaseRequestNumber();
//
//        purchaseRequest.setPurchaseRequestNumber(String.valueOf(purchaseRequest));

    public PurchaseRequestDto entityToDto(PurchaseRequestEntity purchaseRequestEntity) {
        PurchaseRequestDto purchaseRequestDto = new PurchaseRequestDto();
        purchaseRequestDto.setId(purchaseRequestEntity.getId());
        // Map basic fields
        purchaseRequestDto.setDate(purchaseRequestEntity.getDate());
        purchaseRequestDto.setStationId(purchaseRequestEntity.getStation().getStation_id()); // Assuming Station is
        // an entity with an id
        purchaseRequestDto.setReason(purchaseRequestEntity.getReason());

        // Map request items (convert each PurchaseRequestItemEntity to PurchaseRequestItemDto)
        List<PurchaseRequestItemDto> itemDtos = purchaseRequestEntity.getRequestItems().stream().map(itemEntity -> {
            PurchaseRequestItemDto itemDto = new PurchaseRequestItemDto();
            itemDto.setId(itemEntity.getId());
            itemDto.setDate(itemEntity.getDate());
            itemDto.setRef(itemEntity.getRef());
            itemDto.setNumber(itemEntity.getItemNumber());
            itemDto.setDescription(itemEntity.getItemDescription());
            itemDto.setUnitPrice(itemEntity.getUnitPrice());
            itemDto.setEstimatedValue(itemEntity.getEstimatedValue());
            itemDto.setQuantity(itemEntity.getQuantity());
            return itemDto;
        }).collect(Collectors.toList());

        purchaseRequestDto.setRequestItems(itemDtos);
        purchaseRequestDto.setStatus(purchaseRequestEntity.getStatus().name());

        List<PurchaseRequestApprovalDto> approvals = purchaseRequestEntity.getApprovalHistories().stream().map(e -> {
            PurchaseRequestApprovalDto approvalDto = new PurchaseRequestApprovalDto();
            approvalDto.setDate(e.getDate());
            approvalDto.setSignature(e.getSignature());
            approvalDto.setId(e.getId());
            approvalDto.setUser(UserDTOMapper.fromUser(e.getUser()));
            return approvalDto;
        }).toList();
        purchaseRequestDto.setApprovals(approvals);

        return purchaseRequestDto;
    }

    @Override
    public boolean approvePurchaseRequest(UserDTO currentUser, Long purchaseRequestId,
                                          PurchaseRequestApprovalDto purchaseRequestApprovalDto) throws Exception {
        PurchaseRequestEntity purchaseRequest =
                purchaseRequestRepo.findById(purchaseRequestId).orElseThrow(() -> new ResourceNotFoundException(
                        "Invalid Purchase Request Id: " + purchaseRequestId));

        String userRole = currentUser.getRoleName();
        PurchaseRequestStatusEnum currentStatus = purchaseRequest.getStatus();
        PurchaseRequestStatusEnum nextStatus = currentStatus.getNext();

        // Validate if the user has the required role to approve the current status
        if (!nextStatus.getRoles().contains(userRole)) {
            throw new NotAuthorizedException("User role not authorized to approve/reject this purchase request");
        }

        if (purchaseRequestApprovalDto.isApprove()) {

            // Ensure user is authorized for the next stage
            if (!nextStatus.getRoles().contains(userRole)) {
                throw new NotAuthorizedException("User role not authorized for the next approval stage");
            }
        } else { // Handle rejection
            if (currentStatus.equals(PurchaseRequestStatusEnum.RECEIVED)) {
                throw new BadRequestException("Purchase Request is already received and cannot be rejected");
            }
            nextStatus = PurchaseRequestStatusEnum.REJECTED;
        }
        purchaseRequest.setStatus(nextStatus);
        addApprovalHistory(currentUser, purchaseRequest, purchaseRequestApprovalDto.getSignature(), nextStatus);
        purchaseRequestRepo.save(purchaseRequest);
        return true;
    }

}